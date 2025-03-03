package org.example.repository;

import org.example.DatabaseManager;
import org.example.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private Connection connection;
    public CategoryRepository() {
        connection = DatabaseManager.getConnection();
    }

    public void addCategory(Category category) throws SQLException {
        String sql = "INSERT INTO category (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getName());
            statement.executeUpdate();
        }
    }

    // Получение категории по ID
    public Category getCategory(int categoryId) throws SQLException {
        String sql = "SELECT * FROM category WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Category(resultSet.getInt("id"), resultSet.getString("name"));
            }
        }
        return null;
    }

    // Получение всех категорий
    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"), resultSet.getString("name")));
            }
        }
        return categories;
    }

    // Обновление категории
    public void updateCategory(Category category) throws SQLException {
        String sql = "UPDATE category SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());
            statement.executeUpdate();
        }
    }

    // Удаление категории
    public void deleteCategory(int categoryId) throws SQLException {
        String sql = "DELETE FROM category WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            statement.executeUpdate();
        }
    }
}
