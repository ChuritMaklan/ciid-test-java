package com.example.ciidjsp.repository;

import com.example.ciidjsp.DatabaseManager;
import com.example.ciidjsp.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
    private Connection connection;

    public PersonRepository() {
        this.connection = DatabaseManager.getConnection();
    }

    // Добавление нового человека
    public void addPerson(Person person) throws SQLException {
        String sql = "INSERT INTO person (name, email, phone, type_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getEmail());
            stmt.setString(3, person.getPhone());
            stmt.setInt(4, person.getTypeId());
            stmt.executeUpdate();
        }
    }

    // Получение человека по ID
    public Person getPerson(int personId) throws SQLException {
        String sql = "SELECT * FROM person WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, personId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Person(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("name"),
                        rs.getInt("type_id")
                );
            }
        }
        return null;
    }

    // Получение всех людей
    public List<Person> getAllPersons() throws SQLException {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                persons.add(new Person(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("name"),
                        rs.getInt("type_id")
                ));
            }
        }
        return persons;
    }
    public List<Person> getAllSuppliers() throws SQLException {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE type_id = 2";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                persons.add(new Person(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("name"),
                        rs.getInt("type_id")
                ));
            }
        }
        return persons;

    }
    public List<Person> getAllCustomers() throws SQLException {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE type_id = 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                persons.add(new Person(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("name"),
                        rs.getInt("type_id")
                ));
            }
        }
        return persons;

    }

    // Обновление человека
    public void updatePerson(Person person) throws SQLException {
        String sql = "UPDATE person SET name = ?, email = ?, phone = ?, type_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getEmail());
            stmt.setString(3, person.getPhone());
            stmt.setInt(4, person.getTypeId());
            stmt.setInt(5, person.getId());
            stmt.executeUpdate();
        }
    }

    // Удаление человека
    public void deletePerson(int id) throws SQLException {
        String sql = "DELETE FROM person WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}