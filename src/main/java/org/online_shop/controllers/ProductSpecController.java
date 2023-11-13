package org.online_shop.controllers;

import org.online_shop.models.ProductSpec;
import org.online_shop.repositories.ProductSpecRepository;

public class ProductSpecController {

    private final ProductSpecRepository _productRepository;

    public ProductSpecController(ProductSpecRepository productSpecRepository) {
        this._productRepository = productSpecRepository;
    }
}
