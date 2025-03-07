package com.example.ciidtestspring.dto;

import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Getter
public class OrderRequest {
    private long personId;
    private Date orderDate;
    private Set<OrderItemRequest> orderItems;
}
