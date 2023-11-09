package org.online_shop.controllers;

import org.online_shop.repositories.CategoryRepository;

public class CategoryController {

    private final CategoryRepository _categoryRepository;
    public CategoryController(CategoryRepository categoryRepository) {
        _categoryRepository = categoryRepository;
    }
}
