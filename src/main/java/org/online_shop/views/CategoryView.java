package org.online_shop.views;

import org.online_shop.models.Category;

import java.util.List;

public class CategoryView {
    public void view(Category category) {
        System.out.println(category);
    }

    public void viewAll(List<Category> categories) {
        for (Category category:categories) {
            System.out.println(category);
        }
    }
}
