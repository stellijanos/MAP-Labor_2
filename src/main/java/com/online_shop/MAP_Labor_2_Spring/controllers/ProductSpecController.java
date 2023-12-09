package com.online_shop.MAP_Labor_2_Spring.controllers;

import org.online_shop.models.ProductSpec;
import com.online_shop.MAP_Labor_2_Spring.models.repositories.ProductSpecRepository;

public class ProductSpecController {

    private final ProductSpecRepository _productRepository;

    public ProductSpecController(ProductSpecRepository productSpecRepository) {
        this._productRepository = productSpecRepository;
    }
}
