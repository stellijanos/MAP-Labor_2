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


    /**
     * Endpoint to create a new product.
     * Handles POST requests to create a new product.
     *
     * @param product Product object containing product details
     * @return ResponseEntity containing the created product information
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody final Product product) {
        return productService.createProduct(product);
    }

    /**
     * Endpoint to retrieve all products.
     * Handles GET requests to retrieve all products.
     *
     * @return ResponseEntity containing a list of all products
     */
    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Endpoint to retrieve a specific product by product ID.
     * Handles GET requests to retrieve a product by its ID.
     *
     * @param product_id ID of the product to retrieve
     * @return ResponseEntity containing the product information
     */
    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProduct(@PathVariable final Long product_id) {
        return productService.getProduct(product_id);
    }

    /**
     * Endpoint to update product information.
     * Handles PUT requests to update product details.
     *
     * @param product Product object containing updated product information
     * @return ResponseEntity containing the updated product information
     */
    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody final Product product) {
        return productService.updateProduct(product);
    }

    /**
     * Endpoint to delete a product by product ID.
     * Handles DELETE requests to delete a product by its ID.
     *
     * @param product_id ID of the product to delete
     * @return ResponseEntity indicating the deletion status
     */
    @DeleteMapping("/{product_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable final Long product_id) {
        return productService.deleteProduct(product_id);
    }

    /**
     * Endpoint to retrieve all product specifications for a specific product.
     * Handles GET requests to retrieve all specifications of a product by its ID.
     *
     * @param product_id ID of the product whose specifications are to be retrieved
     * @return ResponseEntity containing a list of specifications associated with the product
     */
    @GetMapping("/{product_id}/specs")
    public ResponseEntity<Iterable<ProductSpec>> getAllProductSpecs(@PathVariable final Long product_id) {
        return productService.getAllProductSpecs(product_id);
    }

    /**
     * Endpoint to create a new product specification for a specific product.
     * Handles POST requests to create a new product specification for a product by product_id and specification details.
     *
     * @param product_id ID of the product to which the specification belongs
     * @param productSpec ProductSpec object containing specification details
     * @return ResponseEntity containing the created product specification information
     */
    @PostMapping("/{product_id}/specs")
    public ResponseEntity<ProductSpec> createProductSpec(@PathVariable final Long product_id, @RequestBody final ProductSpec productSpec) {
        return productService.createProductSpec(product_id, productSpec);
    }

    /**
     * Endpoint to retrieve a specific product specification by product ID and specification ID.
     * Handles GET requests to retrieve a product specification by product_id and spec_id.
     *
     * @param product_id ID of the product to which the specification belongs
     * @param spec_id ID of the product specification to retrieve
     * @return ResponseEntity containing the product specification information
     */
    @GetMapping("/{product_id}/specs/{spec_id}")
    public ResponseEntity<ProductSpec> getProductSpec(@PathVariable final Long product_id, @PathVariable final Long spec_id) {
        return productService.getProductSpec(product_id, spec_id);
    }

    /**
     * Endpoint to update a product specification for a specific product.
     * Handles PUT requests to update a product specification for a product by product_id and updated specification details.
     *
     * @param product_id ID of the product to which the specification belongs
     * @param productSpec ProductSpec object containing updated specification information
     * @return ResponseEntity containing the updated product specification information
     */
    @PutMapping("/{product_id}/specs")
    public ResponseEntity<ProductSpec> updateProductSpec(@PathVariable final Long product_id,  @RequestBody final ProductSpec productSpec) {
        return productService.updateProductSpec(product_id, productSpec);
    }

    /**
     * Endpoint to delete a specific product specification of a product by product ID and specification ID.
     * Handles DELETE requests to delete a product specification by product_id and spec_id.
     *
     * @param product_id ID of the product to which the specification belongs
     * @param spec_id ID of the product specification to delete
     * @return ResponseEntity indicating the deletion status
     */
    @DeleteMapping("/{product_id}/specs/{spec_id}")
    public ResponseEntity<String> deleteProductSpec(@PathVariable final Long product_id, @PathVariable final Long spec_id) {
        return productService.deleteProductSpec(product_id, spec_id);
    }

    /**
     * Endpoint to delete all products.
     * Handles DELETE requests to delete all products.
     *
     * @return ResponseEntity indicating the deletion status
     */
    @DeleteMapping
    public ResponseEntity<String> deleteAllProducts() {
        return productService.deleteAllProducts();
    }

}
