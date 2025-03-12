package com.example.ciidtestspring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class OrderRequest {
    private long id;
    private long personId;
    private Date orderDate;
    private Set<OrderItemRequest> orderItems;
}
