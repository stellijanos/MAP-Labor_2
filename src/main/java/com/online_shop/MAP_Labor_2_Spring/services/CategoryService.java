package com.online_shop.MAP_Labor_2_Spring.services;

import com.online_shop.MAP_Labor_2_Spring.models.Category;
import com.online_shop.MAP_Labor_2_Spring.models.Product;
import com.online_shop.MAP_Labor_2_Spring.repositories.CategoryRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<Category> createCategory(Category category) {
        return categoryRepository.existsByName(category.getName()) ? ResponseEntity.status(HttpStatus.CONFLICT).build()
                : ResponseEntity.ok(categoryRepository.save(category));
    }

    public ResponseEntity<Category> getCategory(Long id) {
        return categoryRepository.findById(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Iterable<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    public ResponseEntity<Category> updateCategory(Category category) {

        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());

        if (optionalCategory.isEmpty())
            return ResponseEntity.status(404).build();

        Category existingCategory = optionalCategory.get();

        if (!existingCategory.getName().equals(category.getName()) && categoryRepository.existsByName(category.getName()))
            return ResponseEntity.status(409).build();

        existingCategory.setName(category.getName());

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    public ResponseEntity<String> deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Category deleted successfully!");
    }

    public ResponseEntity<Iterable<Product>> getAllProductsByCategory(Long categoryId) {
        return ResponseEntity.ok(productRepository.findAllByCategoryId(categoryId));
    }

}
