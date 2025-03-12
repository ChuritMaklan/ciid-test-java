package com.example.ciidtestspring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private long id;
    private long partId;
    private int quantity;
    private int price;
}
