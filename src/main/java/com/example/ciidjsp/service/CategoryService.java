package com.example.ciidjsp.service;

import com.example.ciidjsp.model.Category;
import com.example.ciidjsp.repository.CategoryRepository;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {
    private CategoryRepository categoryRepository = new CategoryRepository();

    // Add a new category
    public void addCategory(String name) throws SQLException {
        Category category = new Category(name);
        categoryRepository.addCategory(category);
    }

    // Get category by ID
    public Category getCategoryById(int id) throws SQLException {
        return categoryRepository.getCategory(id);
    }

    // Update a category
    public void updateCategory(int id, String name) throws SQLException {
        Category category = new Category(id, name);
        categoryRepository.updateCategory(category);
    }

    // Delete a category
    public void deleteCategory(int id) throws SQLException {
        categoryRepository.deleteCategory(id);
    }

    // Get all categories
    public List<Category> getAllCategories() throws SQLException {
        return categoryRepository.getAllCategories();
    }
}
