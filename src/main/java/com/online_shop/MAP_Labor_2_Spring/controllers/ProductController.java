package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.Product;
import com.online_shop.MAP_Labor_2_Spring.models.ProductSpec;
import com.online_shop.MAP_Labor_2_Spring.repositories.ProductRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.ProductSpecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductRepository productRepository;
    private final ProductSpecRepository productSpecRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductSpecRepository productSpecRepository) {
        this.productRepository = productRepository;
        this.productSpecRepository = productSpecRepository;

    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody final Product product) {
        product.setImageLink(generateImageLink(product.getName()));
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProduct(@PathVariable final Long product_id) {
        return productRepository.findById(product_id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<Product> updateProduct(@PathVariable final Long product_id, @RequestBody final Product product) {
        return productRepository.findById(product_id)
                .map(existingProduct -> ResponseEntity.ok(productRepository.save(product)))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{product_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable final Long product_id) {
        return productRepository.findById(product_id)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.ok().body("Product deleted successfully!");
                }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{product_id}/specs")
    public ResponseEntity<Iterable<ProductSpec>> getAllProductSpecs(@PathVariable final Long product_id) {
        return productRepository.findById(product_id)
                .map(product -> ResponseEntity.ok(productSpecRepository.findAllByProductId(product_id)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{product_id}/specs")
    public ResponseEntity<ProductSpec> createProductSpec(@PathVariable final Long product_id, @RequestBody final ProductSpec productSpec) {
        return productRepository.findById(product_id)
                .map(product -> {
                    productSpec.setProduct(product);
                    return ResponseEntity.status(HttpStatus.CREATED).body(productSpecRepository.save(productSpec));
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{product_id}/specs/{spec_id}")
    public ResponseEntity<ProductSpec> getProductSpec(@PathVariable final Long product_id, @PathVariable final Long spec_id) {
        return productRepository.findById(product_id)
                .map(product -> productSpecRepository.findById(spec_id)
                        .map(productSpec -> ResponseEntity.ok().body(productSpec))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{product_id}/specs/{spec_id}")
    public ResponseEntity<ProductSpec> updateProductSpec(@PathVariable final Long product_id, @PathVariable final Long spec_id, @RequestBody final ProductSpec productSpec) {
        return productRepository.findById(product_id)
                .map(product -> productSpecRepository.findById(spec_id)
                        .map(existingProductSpec -> ResponseEntity.ok().body(productSpecRepository.save(productSpec)))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{product_id}/specs/{spec_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable final Long product_id, @PathVariable final Long spec_id) {
        return productRepository.findById(product_id)
                .map(product-> productSpecRepository.findById(spec_id)
                        .map(productSpec -> {
                            productSpecRepository.deleteById(spec_id);
                            return ResponseEntity.ok().body("Product spec deleted successfully");
                        })
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }


    public String generateImageLink(String name) {
        return name + CustomControllerTools.getCurrentDateTIme();
    }

}
