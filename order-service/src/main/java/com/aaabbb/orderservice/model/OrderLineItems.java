package com.aaabbb.orderservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItems {
     private Long id;
     private String skuCode;
     private BigDecimal price;
     private Integer quantity;
}
