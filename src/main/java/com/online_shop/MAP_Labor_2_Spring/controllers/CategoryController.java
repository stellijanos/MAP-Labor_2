package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.Category;
import com.online_shop.MAP_Labor_2_Spring.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody final Category category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}
