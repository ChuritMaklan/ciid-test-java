package com.example.ciidtestspring.controller;

import com.example.ciidtestspring.dto.OrderRequest;
import com.example.ciidtestspring.entity.Order;
import com.example.ciidtestspring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderRequest> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderRequest getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public OrderRequest createOrder(@RequestBody OrderRequest orderRequest) {
        Order createdOrder = orderService.createOrder(orderRequest);
        return orderRequest;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody OrderRequest orderRequest) {
        Order updatedOrder = orderService.updateOrder(orderRequest);
        return ResponseEntity.status(updatedOrder == null ? HttpStatus.BAD_REQUEST: HttpStatus.CREATED).body(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
