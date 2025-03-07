package com.example.ciidtestspring.repository;

import com.example.ciidtestspring.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
