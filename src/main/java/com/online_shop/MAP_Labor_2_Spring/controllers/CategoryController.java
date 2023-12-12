package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.Category;
import com.online_shop.MAP_Labor_2_Spring.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/create")
    public @ResponseBody Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @GetMapping("/{id}")
    public @ResponseBody Category getCategory(@PathVariable Long id) {
        return categoryRepository.findById(id).orElse(new Category());
    }

    @GetMapping
    public @ResponseBody List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @PutMapping("/{id}")
    public @ResponseBody Category updateCategory(@PathVariable Long id, @RequestBody final Category category) {
        return categoryRepository.save(category);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Category deleted successfully!");
    }
}
