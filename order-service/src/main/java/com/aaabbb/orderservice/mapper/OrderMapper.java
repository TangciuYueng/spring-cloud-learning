package com.aaabbb.orderservice.mapper;

import com.aaabbb.orderservice.model.Order;
import com.aaabbb.orderservice.model.OrderLineItems;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

// 这个接口其实有问题，不符合设计原则，但是我实在找不到方法如何实现该功能，暂且这样
// 找到方法了，多谢冲哥指导，我们只用@Transactional注解
@Mapper
public interface OrderMapper {

    void insertOrder(Order order);


//    default void placeOrder(@Param("order") Order order) {
//        insertOrder(order);
//        Long orderId = order.getId();
//        List<OrderLineItems> orderLineItemsList = order.getOrderLineItemsList();
//        if (orderLineItemsList != null && !orderLineItemsList.isEmpty()) {
//            insertOrderLineItems(orderId, orderLineItemsList);
//        }
//    }
}
