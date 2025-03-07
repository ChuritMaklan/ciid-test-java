package com.example.ciidtestspring.service;
import com.example.ciidtestspring.dto.OrderItemRequest;
import com.example.ciidtestspring.dto.OrderRequest;
import com.example.ciidtestspring.entity.Order;
import com.example.ciidtestspring.entity.OrderItem;
import com.example.ciidtestspring.entity.Part;
import com.example.ciidtestspring.entity.Person;
import com.example.ciidtestspring.repository.OrderRepository;
import com.example.ciidtestspring.repository.PartRepository;
import com.example.ciidtestspring.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private  PersonTypeService personTypeService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order createOrder(OrderRequest orderRequest) {
        if (!personTypeService.checkIfCustomer(orderRequest.getPersonId())){
            return null;
        }
        Order order = new Order();
        Person person = personRepository.findById(orderRequest.getPersonId())
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + orderRequest.getPersonId()));
        order.setPerson(person);
        order.setOrderDate(orderRequest.getOrderDate());
        Set<OrderItem> orderItems = orderRequest.getOrderItems().stream()
                .map(orderItemRequest -> {
                    Part part = partRepository.findById(orderItemRequest.getPartId())
                            .orElseThrow(() -> new RuntimeException("Part not found with ID: " + orderItemRequest.getPartId()));
                    OrderItem orderItem = new OrderItem();
                    orderItem.setPart(part);
                    orderItem.setQuantity(orderItemRequest.getQuantity());
                    orderItem.setPrice(orderItemRequest.getPrice());
                    orderItem.setOrder(order);

                    return orderItem;
                })
                .collect(Collectors.toSet());
        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrder(Long orderId, OrderRequest orderRequest) {
        if (!personTypeService.checkIfCustomer(orderRequest.getPersonId())){
            return null;
        }
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        existingOrder.setOrderDate(orderRequest.getOrderDate());
        Person person = personRepository.findById(orderRequest.getPersonId())
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + orderRequest.getPersonId()));
        existingOrder.setPerson(person);
        existingOrder.getOrderItems().clear();
        for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(existingOrder);

            Part part = partRepository.findById(itemRequest.getPartId())
                    .orElseThrow(() -> new RuntimeException("Part not found with ID: " + itemRequest.getPartId()));
            orderItem.setPart(part);

            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(itemRequest.getPrice());

            existingOrder.getOrderItems().add(orderItem);
        }
        return orderRepository.save(existingOrder);
    }
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Long> getOrderItemIdsByOrderId(Long orderId) {
        return orderRepository.findById(orderId)
                .map(order -> order.getOrderItems().stream()
                        .map(OrderItem::getId)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}