package com.example.ciidtestspring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PartRequest {
    private long id;
    private String name;
    private long personId;
    private int price;
    private int quantityInStock;
    private String description;
    private Set<Long> categoryIds;

}
