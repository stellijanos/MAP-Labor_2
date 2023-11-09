package org.online_shop.repositories;

import org.online_shop.models.Category;
import org.online_shop.models.DatabaseInMemory;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository extends DatabaseInMemory {

    public boolean create(Category category) {
        return _categories.add(category);
    }

    public Category read(int id) {
        for (Category category : _categories) {
            if (category.get_id() == id)
                return category;
        }
        return new Category();
    }

    public List<Category> readAll() {
        return _categories;
    }

    public boolean update(Category updatedCategory) {
        for (int i = 0; i <= _categories.size(); i++)
            if (_categories.get(i).get_id() == updatedCategory.get_id()) {
                _categories.get(i).set_name(updatedCategory.get_name());
                return true;
            }
        return false;
    }

    public boolean delete(int id) {
        return _categories.removeIf(category -> category.get_id() == id);
    }

    public boolean deleteAll() {
        _categories = new ArrayList<>();
        return _categories.equals(new ArrayList<>());
    }
}
