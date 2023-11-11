package org.online_shop.repositories;

import org.online_shop.models.Category;
import org.online_shop.models.DatabaseInMemory;

import java.util.List;

public class CategoryRepository extends DatabaseInMemory {

    public boolean create(Category category) {
        return _categories.add(category);
    }

    public Category read(Integer id) {
        return _categories.stream()
                .filter(category -> category.get_id().equals(id))
                .findFirst().orElse(new Category());

    }

    public List<Category> readAll() {
        return _categories;
    }

    public boolean update(Category updatedCategory) {
        return _categories.stream()
                .filter(category -> category.get_id().equals(updatedCategory.get_id()))
                .findFirst().map(category -> {
                    category.set_name(updatedCategory.get_name());
                    return true;
                }).orElse(false);
    }

    public boolean delete(Integer id) {
        return _categories.removeIf(category -> category.get_id().equals(id));
    }

    public boolean deleteAll() {
        _categories.clear();
        return true;
    }
}
