package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.Category;
import com.online_shop.MAP_Labor_2_Spring.models.Product;
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

    /**
     * Endpoint to create a new category.
     * Handles POST requests to create a new category.
     *
     * @param category Category object containing category details
     * @return ResponseEntity containing the created category information
     */
    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    /**
     * Endpoint to retrieve a specific category by ID.
     * Handles GET requests to retrieve a category by its ID.
     *
     * @param id ID of the category to retrieve
     * @return ResponseEntity containing the category information
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    /**
     * Endpoint to retrieve all categories.
     * Handles GET requests to retrieve all categories.
     *
     * @return ResponseEntity containing a list of all categories
     */
    @GetMapping
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Endpoint to update category information.
     * Handles PUT requests to update category details.
     *
     * @param category Category object containing updated category information
     * @return ResponseEntity containing the updated category information
     */
    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody final Category category) {
        return categoryService.updateCategory(category);
    }

    /**
     * Endpoint to delete a category by ID.
     * Handles DELETE requests to delete a category by its ID.
     *
     * @param id ID of the category to delete
     * @return ResponseEntity indicating the deletion status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    /**
     * Endpoint to retrieve all products under a specific category.
     * Handles GET requests to retrieve all products under a category by its ID.
     *
     * @param id ID of the category to retrieve products from
     * @return ResponseEntity containing a list of products belonging to the category
     */
    @GetMapping("/{id}/products")
    public ResponseEntity<Iterable<Product>> getAllProductsByCategory(@PathVariable Long id) {
        return categoryService.getAllProductsByCategory(id);
    }
}
