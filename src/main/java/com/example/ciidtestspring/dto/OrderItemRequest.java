package com.example.ciidtestspring.dto;

import lombok.Getter;

@Getter
public class OrderItemRequest {
    private long partId;
    private int quantity;
    private int price;
}
