package org.example.repository;

import org.example.DatabaseManager;
import org.example.model.PersonType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonTypeRepository {
    private Connection connection;

    public PersonTypeRepository() {
        this.connection = DatabaseManager.getConnection();
    }

    // Добавление нового типа человека
    public void addPersonType(PersonType personType) throws SQLException {
        String sql = "INSERT INTO person_type (type_name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, personType.getTypeName());
            stmt.executeUpdate();
        }
    }

    // Получение типа человека по ID
    public PersonType getPersonType(int personTypeId) throws SQLException {
        String sql = "SELECT * FROM person_type WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, personTypeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PersonType(
                        rs.getInt("id"),
                        rs.getString("type_name")
                );
            }
        }
        return null;
    }

    // Получение всех типов людей
    public List<PersonType> getAllPersonTypes() throws SQLException {
        List<PersonType> personTypes = new ArrayList<>();
        String sql = "SELECT * FROM person_type";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                personTypes.add(new PersonType(
                        rs.getInt("id"),
                        rs.getString("type_name")
                ));
            }
        }
        return personTypes;
    }

    // Обновление типа человека
    public void updatePersonType(PersonType personType) throws SQLException {
        String sql = "UPDATE person_type SET type_name = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, personType.getTypeName());
            stmt.setInt(2, personType.getId());
            stmt.executeUpdate();
        }
    }

    // Удаление типа человека
    public void deletePersonType(int personTypeId) throws SQLException {
        String sql = "DELETE FROM person_type WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, personTypeId);
            stmt.executeUpdate();
        }
    }
}