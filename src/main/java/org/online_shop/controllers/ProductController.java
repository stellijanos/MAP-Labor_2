package org.online_shop.controllers;

import org.online_shop.models.Product;
import org.online_shop.repositories.ProductRepository;

public class ProductController {

    private final ProductRepository _productRepository;

    public ProductController(ProductRepository productRepository) {
        _productRepository = productRepository;
    }

    public void createProduct(String name, Float price, Integer categoryId, String description, Integer stock) {

        Product product = new Product();

        product.set_name(name);
        product.set_price(price);
        product.set_categoryId(categoryId);
        product.set_description(description);
        product.set_stock(stock);


//        product.set_id()

    }

}
