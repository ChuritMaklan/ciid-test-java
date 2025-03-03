package org.example.consoleUI;

import org.example.model.PersonType;
import org.example.service.PersonTypeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PersonTypeConsoleUI {
    private static Scanner scanner = new Scanner(System.in);
    private PersonTypeService personTypeService = new PersonTypeService();

    public void addPersonType() {
        System.out.println("Enter person type name:");
        String typeName = scanner.nextLine();

        try {
            personTypeService.addPersonType(typeName);
            System.out.println("Person type added.");
        } catch (SQLException e) {
            System.err.println("Error adding person type: " + e.getMessage());
        }
    }

    public void getPersonTypeById() {
        System.out.println("Enter person type ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            PersonType personType = personTypeService.getPersonTypeById(id);
            if (personType != null) {
                System.out.println(personType);
            } else {
                System.out.println("Person type not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving person type: " + e.getMessage());
        }
    }

    public void updatePersonType() {
        System.out.println("Enter person type ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new person type name:");
        String typeName = scanner.nextLine();

        try {
            personTypeService.updatePersonType(id, typeName);
            System.out.println("Person type updated.");
        } catch (SQLException e) {
            System.err.println("Error updating person type: " + e.getMessage());
        }
    }

    public void deletePersonType() {
        System.out.println("Enter person type ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            personTypeService.deletePersonType(id);
            System.out.println("Person type deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting person type: " + e.getMessage());
        }
    }

    public void getAllPersonTypes() {
        try {
            List<PersonType> personTypes = personTypeService.getAllPersonTypes();
            if (personTypes.isEmpty()) {
                System.out.println("No person types found.");
            } else {
                System.out.println("All person types:");
                personTypes.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving person types: " + e.getMessage());
        }
    }
}
