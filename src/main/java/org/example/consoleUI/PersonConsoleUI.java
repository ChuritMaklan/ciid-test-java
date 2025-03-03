package org.example.consoleUI;

import org.example.model.Person;
import org.example.service.PersonService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PersonConsoleUI {
    private static Scanner scanner = new Scanner(System.in);
    private PersonService personService = new PersonService();

    public void addPerson() {
        System.out.println("Enter person name:");
        String name = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter phone:");
        String phone = scanner.nextLine();
        System.out.println("Enter person type ID:");
        int typeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            personService.addPerson(name, email, phone, typeId);
            System.out.println("Person added.");
        } catch (SQLException e) {
            System.err.println("Error adding person: " + e.getMessage());
        }
    }

    public void getPersonById() {
        System.out.println("Enter person ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            Person person = personService.getPersonById(id);
            if (person != null) {
                System.out.println(person);
            } else {
                System.out.println("Person not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving person: " + e.getMessage());
        }
    }

    public void updatePerson() {
        System.out.println("Enter person ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new name:");
        String name = scanner.nextLine();
        System.out.println("Enter new email:");
        String email = scanner.nextLine();
        System.out.println("Enter new phone:");
        String phone = scanner.nextLine();
        System.out.println("Enter new person type ID:");
        int typeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            personService.updatePerson(id, name, email, phone, typeId);
            System.out.println("Person updated.");
        } catch (SQLException e) {
            System.err.println("Error updating person: " + e.getMessage());
        }
    }

    public void deletePerson() {
        System.out.println("Enter person ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            personService.deletePerson(id);
            System.out.println("Person deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting person: " + e.getMessage());
        }
    }

    public void getAllPersons() {
        try {
            List<Person> persons = personService.getAllPersons();
            if (persons.isEmpty()) {
                System.out.println("No persons found.");
            } else {
                System.out.println("All persons:");
                persons.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving persons: " + e.getMessage());
        }
    }
}
