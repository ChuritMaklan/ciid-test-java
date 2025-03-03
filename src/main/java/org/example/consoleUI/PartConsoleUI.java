package org.example.consoleUI;

import org.example.model.Part;
import org.example.service.PartService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartConsoleUI {
    private static Scanner scanner = new Scanner(System.in);
    private PartService partService = new PartService();

    public void addPart() {
        System.out.println("Enter part name:");
        String name = scanner.nextLine();
        System.out.println("Enter person ID:");
        int personId = scanner.nextInt();
        System.out.println("Enter price:");
        int price = scanner.nextInt();
        System.out.println("Enter quantity in stock:");
        int quantityInStock = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter description:");
        String description = scanner.nextLine();

        // Get category IDs from the user
        List<Integer> categoryIds = getCategoryIdsFromUser();

        try {
            partService.addPart(name, personId, price, description, quantityInStock, categoryIds);
            System.out.println("Part added.");
        } catch (SQLException e) {
            System.err.println("Error adding part: " + e.getMessage());
        }
    }

    private List<Integer> getCategoryIdsFromUser() {
        List<Integer> categoryIds = new ArrayList<>();
        System.out.println("Enter the number of categories for this part:");
        int numberOfCategories = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 1; i <= numberOfCategories; i++) {
            System.out.println("Enter category ID " + i + ":");
            int categoryId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            categoryIds.add(categoryId);
        }
        return categoryIds;
    }

    public void getPartById() {
        System.out.println("Enter part ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            Part part = partService.getPartById(id);
            if (part != null) {
                System.out.println(part);
            } else {
                System.out.println("Part not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving part: " + e.getMessage());
        }
    }

    public void updatePart() {
        System.out.println("Enter part ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new name:");
        String name = scanner.nextLine();
        System.out.println("Enter new person ID:");
        int personId = scanner.nextInt();
        System.out.println("Enter new price:");
        int price = scanner.nextInt();
        System.out.println("Enter new quantity in stock:");
        int quantityInStock = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new description:");
        String description = scanner.nextLine();
        try {
            partService.updatePart(id, name, personId, price, description, quantityInStock);
            System.out.println("Part updated.");
        } catch (SQLException e) {
            System.err.println("Error updating part: " + e.getMessage());
        }
    }

    public void deletePart() {
        System.out.println("Enter part ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            partService.deletePart(id);
            System.out.println("Part deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting part: " + e.getMessage());
        }
    }

    public void getAllParts() {
        try {
            List<Part> parts = partService.getAllParts();
            if (parts.isEmpty()) {
                System.out.println("No parts found.");
            } else {
                System.out.println("All parts:");
                parts.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving parts: " + e.getMessage());
        }
    }

    public void getPartsByCategory() {
        System.out.println("Enter category ID:");
        int categoryId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            List<Part> parts = partService.getPartsByCategory(categoryId);
            if (parts.isEmpty()) {
                System.out.println("No parts found for this category.");
            } else {
                System.out.println("Parts in category " + categoryId + ":");
                parts.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving parts by category: " + e.getMessage());
        }
    }
}

