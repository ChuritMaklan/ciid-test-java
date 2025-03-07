package com.example.ciidtestspring.repository;

import com.example.ciidtestspring.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
