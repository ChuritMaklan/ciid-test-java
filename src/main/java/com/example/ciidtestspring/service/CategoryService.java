package com.example.ciidtestspring.service;

import com.example.ciidtestspring.dto.CategoryRequest;
import com.example.ciidtestspring.entity.Category;
import com.example.ciidtestspring.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }


    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(CategoryRequest updatedCategory) {
        Category category = categoryRepository.findById(updatedCategory.getId()).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(updatedCategory.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}