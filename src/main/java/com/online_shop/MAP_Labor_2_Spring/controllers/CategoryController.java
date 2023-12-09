package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.enums.Response;
import org.online_shop.models.Category;
import com.online_shop.MAP_Labor_2_Spring.models.repositories.CategoryRepository;

import java.util.List;

public class CategoryController {

    private final CategoryRepository _categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        _categoryRepository = categoryRepository;
    }

    public Response createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        category.setId(_categoryRepository.readAll().size() + 1);
        return _categoryRepository.create(category) ? Response.CATEGORY_CREATE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }

    public Category getCategory(Integer id) {
        return _categoryRepository.read(id);
    }

    public List<Category> getAllCategories() {
        return _categoryRepository.readAll();
    }

    public Response modifyCategory(String name, Integer id) {
        Category currentCategory = _categoryRepository.read(id);
        if (currentCategory.getName() == null)
            return Response.CATEGORY_NOT_FOUND;

        Category updatedCategory = new Category();
        updatedCategory.setName(name.isEmpty() ? currentCategory.getName() : name);
        return _categoryRepository.update(updatedCategory) ? Response.CATEGORY_UPDATE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }

    public Response removeCategory(Integer id) {
        return _categoryRepository.delete(id) ? Response.CATEGORY_DELETE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }

    public Response removeAllCategories() {
        return _categoryRepository.deleteAll() ? Response.ALL_CATEGORIES_DELETE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }
}
