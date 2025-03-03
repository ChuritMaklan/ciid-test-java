package org.example.repository;

import org.example.DatabaseManager;
import org.example.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private Connection connection;

    public OrderRepository() {
        this.connection = DatabaseManager.getConnection();
    }

    // Добавление нового заказа
    public int addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO \"order\" (person_id, order_date) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getPersonId());
            stmt.setDate(2, order.getOrderDate());
            stmt.executeUpdate();

            // Retrieve the generated order ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve the generated order ID.");
                }
            }
        }
    }

    // Получение заказа по ID
    public Order getOrder(int orderId) throws SQLException {
        String sql = "SELECT * FROM \"order\" WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Order(
                        resultSet.getInt("id"),
                        resultSet.getInt("client_id"),
                        resultSet.getDate("order_date")
                );
            }
        }
        return null;
    }

    // Получение всех заказов
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM \"order\"";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                orders.add(new Order(
                        resultSet.getInt("id"),
                        resultSet.getInt("client_id"),
                        resultSet.getDate("order_date")
                ));
            }
        }
        return orders;
    }

    // Обновление заказа
    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE \"order\" SET person_id = ?, order_date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getPersonId());
            statement.setDate(2, order.getOrderDate());
            statement.setInt(3, order.getId());
            statement.executeUpdate();
        }
    }

    // Удаление заказа
    public void deleteOrder(int orderId) throws SQLException {
        String sql = "DELETE FROM \"order\" WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
    }
}
