package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.repositories.ProductRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.ProductSpecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product_spec")
public class ProductSpecController {


    private ProductSpecRepository productSpecRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductSpecController(ProductSpecRepository productSpecRepository, ProductRepository productRepository) {
        this.productSpecRepository = productSpecRepository;
        this.productRepository = productRepository;
    }

}
