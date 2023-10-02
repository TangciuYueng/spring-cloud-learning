package com.aaabbb.orderservice.service;

import com.aaabbb.orderservice.dto.InventoryResponse;
import com.aaabbb.orderservice.dto.OrderLineItemsDto;
import com.aaabbb.orderservice.dto.OrderRequest;
import com.aaabbb.orderservice.mapper.OrderLineItemsMapper;
import com.aaabbb.orderservice.mapper.OrderMapper;
import com.aaabbb.orderservice.model.Order;
import com.aaabbb.orderservice.model.OrderLineItems;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    final private OrderMapper orderMapper;
    final private OrderLineItemsMapper orderLineItemsMapper;
    // 配置@LoadBalanced
    final private WebClient.Builder webClientBuilder;
    // multi-table operation
    @Transactional
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
                .toList();
        order.setOrderLineItemsList(orderLineItems);

        // get all the skuCodes from the order
        List<String> skuCodes= order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();


        // Call Inventory Service, and place order if product is in stock
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
//                .uri("http://localhost:8082/api/inventory"
                .uri("http://inventory-service/api/inventory"
                        , uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductsInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);
        if (allProductsInStock ) {
            orderMapper.insertOrder(order);
            orderLineItemsMapper.insertOrderLineItems(order.getId(), order.getOrderLineItemsList());
            return "Order Placed Successfully";
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}
