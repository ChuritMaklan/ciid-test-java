package com.example.ciidjsp.service;

import com.example.ciidjsp.model.Order;
import com.example.ciidjsp.model.OrderItem;
import com.example.ciidjsp.repository.OrderItemRepository;
import com.example.ciidjsp.repository.OrderRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository = new OrderRepository();
    private final OrderItemRepository orderItemRepository = new OrderItemRepository();
    private final PartService partService = new PartService();

    // Add a new order
    public void addOrder(int personId, Date orderDate, int[] selectedIds, int[] quantities) throws SQLException {
        Order order = new Order(personId, orderDate);
        int orderId =  orderRepository.addOrder(order);
        for(int i = 0; i < selectedIds.length; i++){
            addOrderItem(orderId, selectedIds[i], quantities[i], partService.getPartById(selectedIds[i]).getPrice());
        }

    }

    // Add an order item
    public void addOrderItem(int orderId, int partId, int quantity, int price) throws SQLException {
        OrderItem orderItem = new OrderItem(orderId, partId, quantity, price);
        orderItemRepository.addOrderItem(orderItem);
    }

    // Get order by ID
    public Order getOrderById(int id) throws SQLException {
        return orderRepository.getOrder(id);
    }

    public void updateOrder(int id, int personId, Date orderDate) throws SQLException {
        Order order = new Order(id, personId, orderDate);
        orderRepository.updateOrder(order);
    }

    // Delete an order
    public void deleteOrder(int id) throws SQLException {
        orderRepository.deleteOrder(id);
    }

    public List<Order> getAllOrders() throws SQLException {
        return orderRepository.getAllOrders();
    }

    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        return orderItemRepository.getOrderItemsByOrderId(orderId);
    }
}
