package com.example.ciidtestspring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PersonType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", unique = true, nullable = false)
    private String typeName;
}
