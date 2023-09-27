package com.aaabbb.orderservice.mapper;

import com.aaabbb.orderservice.model.OrderLineItems;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderLineItemsMapper {
    void insertOrderLineItems(@Param("orderId") Long orderId, @Param("orderLineItemsList") List<OrderLineItems> orderLineItemsList);
}
