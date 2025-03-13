package com.example.ciidtestspring.service;

import com.example.ciidtestspring.dto.CategoryRequest;
import com.example.ciidtestspring.entity.Category;
import com.example.ciidtestspring.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryRequest> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryRequest> categoryRequests = new ArrayList<>();
        for(Category category: categories){
            categoryRequests.add(categoryToCategoryRequest(category));
        }
        return categoryRequests;
    }

    public CategoryRequest getCategoryById(Long id) {
        return categoryToCategoryRequest(categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found")));
    }


    public Category createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }
    private CategoryRequest categoryToCategoryRequest(Category category){
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setId(category.getId());
        categoryRequest.setName(category.getName());
        return categoryRequest;
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