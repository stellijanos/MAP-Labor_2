package com.online_shop.MAP_Labor_2_Spring.services;

import com.online_shop.MAP_Labor_2_Spring.controllers.CustomControllerTools;
import com.online_shop.MAP_Labor_2_Spring.models.Product;
import com.online_shop.MAP_Labor_2_Spring.models.ProductSpec;
import com.online_shop.MAP_Labor_2_Spring.repositories.ProductRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.ProductSpecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductSpecRepository productSpecRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductSpecRepository productSpecRepository, ProductRepository productRepository) {
        this.productSpecRepository = productSpecRepository;
        this.productRepository = productRepository;
    }


    public ResponseEntity<Product> createProduct(Product product) {
        product.setImageLink(generateImageLink(product.getName()));
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    public ResponseEntity<Product> getProduct(Long product_id) {
        return productRepository.findById(product_id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Product> updateProduct(Product product) {
        return productRepository.findById(product.getId())
                .map(existingProduct -> ResponseEntity.ok(productRepository.save(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> deleteProduct(Long product_id) {
        return productRepository.findById(product_id)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.ok().body("Product deleted successfully!");
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Iterable<ProductSpec>> getAllProductSpecs(Long product_id) {
        return productRepository.findById(product_id)
                .map(product -> ResponseEntity.ok(productSpecRepository.findAllByProductId(product_id)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<ProductSpec> createProductSpec(Long product_id, ProductSpec productSpec) {
        return productRepository.findById(product_id)
                .map(product -> {
                    productSpec.setProduct(product);
                    return ResponseEntity.status(HttpStatus.CREATED).body(productSpecRepository.save(productSpec));
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<ProductSpec> getProductSpec(Long product_id, Long spec_id) {
        return productRepository.findById(product_id)
                .map(product -> productSpecRepository.findById(spec_id)
                        .map(productSpec -> ResponseEntity.ok().body(productSpec))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<ProductSpec> updateProductSpec(Long product_id, ProductSpec productSpec) {
        return productRepository.findById(product_id)
                .map(product -> productSpecRepository.findById(productSpec.getId())
                        .map(existingProductSpec -> ResponseEntity.ok().body(productSpecRepository.save(productSpec)))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> deleteProductSpec(Long product_id, Long spec_id) {
        return productRepository.findById(product_id)
                .map(product -> productSpecRepository.findById(spec_id)
                        .map(productSpec -> {
                            productSpecRepository.deleteById(spec_id);
                            return ResponseEntity.ok().body("Product spec deleted successfully");
                        })
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> deleteAllProducts() {
        productRepository.deleteAll();
        return ResponseEntity.ok().body("All products deleted successfully!");
    }

    public String generateImageLink(String name) {
        return name + CustomControllerTools.getCurrentDateTIme();
    }

}
