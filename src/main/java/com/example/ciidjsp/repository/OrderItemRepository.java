package com.example.ciidjsp.repository;

import com.example.ciidjsp.DatabaseManager;
import com.example.ciidjsp.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository {
    private Connection connection;

    public OrderItemRepository() {
        this.connection = DatabaseManager.getConnection();
    }

    // Добавление нового элемента заказа
    public void addOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "INSERT INTO order_item (order_id, part_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderItem.getOrderId());
            statement.setInt(2, orderItem.getPartId());
            statement.setInt(3, orderItem.getQuantity());
            statement.setInt(4, orderItem.getPrice());
            statement.executeUpdate();
        }
    }

    // Получение элемента заказа по ID
    public OrderItem getOrderItem(int orderItemId) throws SQLException {
        String sql = "SELECT * FROM order_item WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderItemId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new OrderItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("order_id"),
                        resultSet.getInt("part_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("price")
                );
            }
        }
        return null;
    }

    // Получение всех элементов заказа
    public List<OrderItem> getAllOrderItems() throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_item";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                orderItems.add(new OrderItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("order_id"),
                        resultSet.getInt("part_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("price")
                ));
            }
        }
        return orderItems;
    }

    // Обновление элемента заказа
    public void updateOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "UPDATE order_item SET order_id = ?, part_id = ?, quantity = ?, price = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderItem.getOrderId());
            statement.setInt(2, orderItem.getPartId());
            statement.setInt(3, orderItem.getQuantity());
            statement.setInt(4, orderItem.getPrice());
            statement.setInt(5, orderItem.getId());
            statement.executeUpdate();
        }
    }

    // Удаление элемента заказа
    public void deleteOrderItem(int id) throws SQLException {
        String sql = "DELETE FROM order_item WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_item WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderItems.add(new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("part_id"),
                        rs.getInt("quantity"),
                        rs.getInt("price")
                ));
            }
        }
        return orderItems;
    }
}
