package com.example.ciidtestspring.dto;

import lombok.Getter;

import java.util.Set;

@Getter
public class PartRequest {
    private String name;
    private long personId;
    private int price;
    private int quantityInStock;
    private String description;
    private Set<Long> categoryIds;

}
