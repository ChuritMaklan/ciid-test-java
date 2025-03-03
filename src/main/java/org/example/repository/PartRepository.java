package org.example.repository;

import org.example.DatabaseManager;
import org.example.model.Part;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartRepository {
    private Connection connection;

    public PartRepository() {
        this.connection = DatabaseManager.getConnection();
    }

    // Добавление новой детали
    public void addPart(Part part, List<Integer> categoryIds) throws SQLException {
        String sql = "INSERT INTO part (name, person_id, price, description, quantity_in_stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, part.getName());
            stmt.setInt(2, part.getPersonId());
            stmt.setInt(3, part.getPrice());
            stmt.setString(4, part.getDescription());
            stmt.setInt(5, part.getQuantity_in_stock());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int partId = generatedKeys.getInt(1);

                    String sqlPartCategory = "INSERT INTO part_category (part_id, category_id) VALUES (?, ?)";
                    try (PreparedStatement stmtPartCategory = connection.prepareStatement(sqlPartCategory)) {
                        for (int categoryId : categoryIds) {
                            stmtPartCategory.setInt(1, partId);
                            stmtPartCategory.setInt(2, categoryId);
                            stmtPartCategory.addBatch();
                        }
                        stmtPartCategory.executeBatch();
                    }
                } else {
                    throw new SQLException("Failed to retrieve the generated part ID.");
                }
            }
        }
    }

    // Получение детали по ID
    public Part getPart(int partId) throws SQLException {
        String sql = "SELECT * FROM part WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, partId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Part(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("supplier_id"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("quantity_in_stock")
                );
            }
        }
        return null;
    }

    // Получение всех деталей
    public List<Part> getAllParts() throws SQLException {
        List<Part> parts = new ArrayList<>();
        String sql = "SELECT * FROM part";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                parts.add(new Part(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("supplier_id"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("quantity_in_stock")
                ));
            }
        }
        return parts;
    }

    // Обновление детали
    public void updatePart(Part part) throws SQLException {
        String sql = "UPDATE part SET name = ?, person_id = ?, price = ?, description = ?, quantity_in_stock = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, part.getName());
            stmt.setInt(2, part.getPersonId());
            stmt.setInt(3, part.getPrice());
            stmt.setString(4, part.getDescription());
            stmt.setInt(5, part.getQuantity_in_stock());
            stmt.setInt(6, part.getId());
            stmt.executeUpdate();
        }
    }

    // Удаление детали
    public void deletePart(int partId) throws SQLException {
        String sql = "DELETE FROM part WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, partId);
            stmt.executeUpdate();
        }
    }
    public List<Part> getPartsByCategory(int categoryId) throws SQLException {
        List<Part> parts = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.person_id, p.price, p.description, p.quantity_in_stock " +
                "FROM part p " +
                "JOIN part_category pc ON p.id = pc.part_id " +
                "WHERE pc.category_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                parts.add(new Part(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("person_id"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("quantity_in_stock")
                ));
            }
        }
        return parts;
    }
}
