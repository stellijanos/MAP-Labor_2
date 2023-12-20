package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.Product;
import com.online_shop.MAP_Labor_2_Spring.models.ProductSpec;
import com.online_shop.MAP_Labor_2_Spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody final Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProduct(@PathVariable final Long product_id) {
        return productService.getProduct(product_id);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody final Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable final Long product_id) {
        return productService.deleteProduct(product_id);
    }

    @GetMapping("/{product_id}/specs")
    public ResponseEntity<Iterable<ProductSpec>> getAllProductSpecs(@PathVariable final Long product_id) {
        return productService.getAllProductSpecs(product_id);
    }

    @PostMapping("/{product_id}/specs")
    public ResponseEntity<ProductSpec> createProductSpec(@PathVariable final Long product_id, @RequestBody final ProductSpec productSpec) {
        return productService.createProductSpec(product_id, productSpec);
    }

    @GetMapping("/{product_id}/specs/{spec_id}")
    public ResponseEntity<ProductSpec> getProductSpec(@PathVariable final Long product_id, @PathVariable final Long spec_id) {
        return productService.getProductSpec(product_id, spec_id);
    }

    @PutMapping("/{product_id}/specs")
    public ResponseEntity<ProductSpec> updateProductSpec(@PathVariable final Long product_id,  @RequestBody final ProductSpec productSpec) {
        return productService.updateProductSpec(product_id, productSpec);
    }

    @DeleteMapping("/{product_id}/specs/{spec_id}")
    public ResponseEntity<String> deleteProductSpec(@PathVariable final Long product_id, @PathVariable final Long spec_id) {
        return productService.deleteProductSpec(product_id, spec_id);
    }

}
