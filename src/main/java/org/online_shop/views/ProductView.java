package org.online_shop.views;

import org.online_shop.models.Product;

import java.util.List;

public class ProductView {
    public void view(Product product) {
        System.out.println(product);
    }

    public void viewAll(List<Product> products) {
        for (Product product:products) {
            System.out.println(product);
        }
    }
}
