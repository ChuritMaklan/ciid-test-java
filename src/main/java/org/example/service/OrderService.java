package org.example.service;

import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.repository.OrderItemRepository;
import org.example.repository.OrderRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();
    private OrderItemRepository orderItemRepository = new OrderItemRepository();

    // Add a new order
    public int addOrder(int personId, Date orderDate) throws SQLException {
        Order order = new Order(personId, orderDate);
        return orderRepository.addOrder(order);
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

    // Update an order
    public void updateOrder(int id, int personId, Date orderDate) throws SQLException {
        Order order = new Order(id, personId, orderDate);
        orderRepository.updateOrder(order);
    }

    // Delete an order
    public void deleteOrder(int id) throws SQLException {
        orderRepository.deleteOrder(id);
    }

    // Get all orders
    public List<Order> getAllOrders() throws SQLException {
        return orderRepository.getAllOrders();
    }

    // Get order items by order ID
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        return orderItemRepository.getOrderItemsByOrderId(orderId);
    }
}
