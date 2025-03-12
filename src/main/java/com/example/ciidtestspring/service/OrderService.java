package com.example.ciidtestspring.service;
import com.example.ciidtestspring.PersonTypeEnum;
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

import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PartRepository partRepository;
    private final PersonRepository personRepository;
    private final PersonTypeService personTypeService;

    public OrderService(OrderRepository orderRepository, PartRepository partRepository, PersonRepository personRepository, PersonTypeService personTypeService) {
        this.orderRepository = orderRepository;
        this.partRepository = partRepository;
        this.personRepository = personRepository;
        this.personTypeService = personTypeService;
    }

    public List<OrderRequest> getAllOrders() {
        List<OrderRequest> orderRequests = new ArrayList<>();
        List<Order> orders =  orderRepository.findAll();
        for(Order order: orders){
            orderRequests.add(orderToOrderRequest(order));
        }
        return orderRequests;
    }

    public OrderRequest getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return orderToOrderRequest(order);
    }
    private OrderRequest orderToOrderRequest(Order order){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setId(order.getId());
        orderRequest.setPersonId(order.getPerson().getId());
        orderRequest.setOrderDate(order.getOrderDate());
        Set<OrderItemRequest> orderItemRequests = new HashSet<>();
        Set<OrderItem> orderItems = order.getOrderItems();
        for(OrderItem orderItem : orderItems){
            OrderItemRequest orderItemRequest = new OrderItemRequest();
            orderItemRequest.setId(orderItem.getId());
            orderItemRequest.setPrice(orderItem.getPrice());
            orderItemRequest.setQuantity(orderItem.getQuantity());
            orderItemRequest.setPartId(orderItem.getPart().getId());
            orderItemRequests.add(orderItemRequest);
        }
        orderRequest.setOrderItems(orderItemRequests);
        return orderRequest;
    }

    public Order createOrder(OrderRequest orderRequest) {
        if (orderRequest.getPersonId() == PersonTypeEnum.CUSTOMER.getId()){
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
    public Order updateOrder(OrderRequest orderRequest) {
        if (orderRequest.getPersonId() == PersonTypeEnum.CUSTOMER.getId()){
            return null;
        }
        Long orderId = orderRequest.getId();
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