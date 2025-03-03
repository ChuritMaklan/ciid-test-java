package org.example.consoleUI;

import org.example.model.Category;
import org.example.service.CategoryService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CategoryConsoleUI {
    private static Scanner scanner = new Scanner(System.in);
    private CategoryService categoryService = new CategoryService();

    public void addCategory() {
        System.out.println("Enter category name:");
        String name = scanner.nextLine();
        try {
            categoryService.addCategory(name);
            System.out.println("Category added.");
        } catch (SQLException e) {
            System.err.println("Error adding category: " + e.getMessage());
        }
    }

    public void getCategoryById() {
        System.out.println("Enter category ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            Category category = categoryService.getCategoryById(id);
            if (category != null) {
                System.out.println(category);
            } else {
                System.out.println("Category not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving category: " + e.getMessage());
        }
    }

    public void updateCategory() {
        System.out.println("Enter category ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new category name:");
        String name = scanner.nextLine();
        try {
            categoryService.updateCategory(id, name);
            System.out.println("Category updated.");
        } catch (SQLException e) {
            System.err.println("Error updating category: " + e.getMessage());
        }
    }

    public void deleteCategory() {
        System.out.println("Enter category ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            categoryService.deleteCategory(id);
            System.out.println("Category deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting category: " + e.getMessage());
        }
    }

    public void getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            categories.forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("Error retrieving categories: " + e.getMessage());
        }
    }
}
