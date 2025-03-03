package org.example.repository;

import org.example.DatabaseManager;
import org.example.model.PartCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartCategoryRepository {
    private Connection connection;

    public PartCategoryRepository() {
        this.connection = DatabaseManager.getConnection();
    }

    // Добавление новой связи между деталью и категорией
    public void addPartCategory(PartCategory partCategory) throws SQLException {
        String sql = "INSERT INTO part_category (part_id, category_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, partCategory.getPartId());
            statement.setInt(2, partCategory.getCategoryId());
            statement.executeUpdate();
        }
    }

    // Получение связи по ID детали
    public PartCategory getPartCategory(int partId) throws SQLException {
        String sql = "SELECT * FROM part_category WHERE part_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, partId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new PartCategory(
                        resultSet.getInt("part_id"),
                        resultSet.getInt("category_id")
                );
            }
        }
        return null;
    }

    // Получение всех связей
    public List<PartCategory> getAllPartCategories() throws SQLException {
        List<PartCategory> partCategories = new ArrayList<>();
        String sql = "SELECT * FROM part_category";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                partCategories.add(new PartCategory(
                        rs.getInt("part_id"),
                        rs.getInt("category_id")
                ));
            }
        }
        return partCategories;
    }

    // Удаление связи по ID детали
    public void deletePartCategory(int partId) throws SQLException {
        String sql = "DELETE FROM part_category WHERE part_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, partId);
            statement.executeUpdate();
        }
    }
}
