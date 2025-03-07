package com.example.ciidtestspring.dto;

import com.example.ciidtestspring.entity.PersonType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
public class PersonRequest {
    private long id;
    private String name;

    private String email;

    private String phone;

    private long typeId;
}
