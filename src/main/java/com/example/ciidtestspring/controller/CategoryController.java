package com.example.ciidtestspring.controller;

import com.example.ciidtestspring.dto.CategoryRequest;
import com.example.ciidtestspring.entity.Category;
import com.example.ciidtestspring.service.CategoryService;
import com.example.ciidtestspring.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final PartService partService;

    public CategoryController(CategoryService categoryService, PartService partService) {
        this.categoryService = categoryService;
        this.partService = partService;
    }

    @GetMapping
    public List<CategoryRequest> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryRequest getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }


    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest) {
        Category createdCategory = categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        Category updatedCategory = categoryService.updateCategory(categoryRequest);
        if (updatedCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("/{id}/parts")
    public ResponseEntity<List<Long>> getPartsByCategoryId(@PathVariable Long id) {
        List<Long> parts = partService.getPartsByCategoryId(id);
        return ResponseEntity.ok(parts);
    }
}
