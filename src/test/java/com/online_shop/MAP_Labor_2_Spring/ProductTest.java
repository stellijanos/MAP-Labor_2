package com.online_shop.MAP_Labor_2_Spring;


import com.online_shop.MAP_Labor_2_Spring.controllers.ProductController;
import com.online_shop.MAP_Labor_2_Spring.models.Category;
import com.online_shop.MAP_Labor_2_Spring.models.Product;
import com.online_shop.MAP_Labor_2_Spring.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_createProduct() {
        Category electronics = new Category();
        electronics.setName("Electronics");

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(2500F);
        product.setCategory(electronics);
        product.setStock(50);
        product.setDescription("Very good laptop.");
        product.setImageLink("/laptop1.png");

        when(productService.createProduct(product)).thenReturn(ResponseEntity.status(201).body(product));

        ResponseEntity<Product> response = productController.createProduct(product);

        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());

        Product createdProduct = response.getBody();
        assertNotNull(createdProduct);
        assertEquals(1L, createdProduct.getId());
        assertEquals("Laptop", createdProduct.getName());

    }

    @Test
    void test_getAllProducts() {
        List<Product> products = new ArrayList<>();

        Category electronics = new Category();
        electronics.setName("Electronics");

        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Laptop");
        p1.setPrice(2500F);
        p1.setCategory(electronics);
        p1.setStock(50);
        p1.setDescription("Very good laptop.");
        p1.setImageLink("/laptop1.png");

        Product p2 = new Product();
        p1.setId(2L);
        p1.setName("Smartphone");
        p1.setPrice(1500F);
        p1.setCategory(electronics);
        p1.setStock(20);
        p1.setDescription("Very good phone.");
        p1.setImageLink("/phone1.png");

        products.add(p1);
        products.add(p2);

        ResponseEntity<Iterable<Product>> finalProducts = ResponseEntity.ok(products);

        when(productService.getAllProducts()).thenReturn(finalProducts);

        Iterable<Product> result = productController.getAllProducts().getBody();

        assertNotNull(result);
        assertEquals(2, ((List<Product>) result).size());

        assertEquals(p1.getId(), products.get(0).getId());
        assertEquals(p1.getName(), products.get(0).getName());
        assertEquals(p1.getPrice(), products.get(0).getPrice());
        assertEquals(p1.getCategory(), products.get(0).getCategory());
        assertEquals(p1.getStock(), products.get(0).getStock());
        assertEquals(p1.getDescription(), products.get(0).getDescription());
        assertEquals(p1.getImageLink(), products.get(0).getImageLink());

        assertEquals(p2.getId(), products.get(1).getId());
        assertEquals(p2.getName(), products.get(1).getName());
        assertEquals(p2.getPrice(), products.get(1).getPrice());
        assertEquals(p2.getCategory(), products.get(1).getCategory());
        assertEquals(p2.getStock(), products.get(1).getStock());
        assertEquals(p2.getDescription(), products.get(1).getDescription());
        assertEquals(p2.getImageLink(), products.get(1).getImageLink());
    }


    @Test
    void test_getProductById() {
        long productId = 1L;
        Category electronics = new Category();
        electronics.setName("Electronics");

        Product product = new Product();
        product.setId(productId);
        product.setName("Laptop");
        product.setPrice(2500F);
        product.setCategory(electronics);
        product.setStock(50);
        product.setDescription("Very good laptop.");
        product.setImageLink("/laptop1.png");

        when(productService.getProduct(productId)).thenReturn(ResponseEntity.ok(product));

        ResponseEntity<Product> response = productController.getProduct(productId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());

        Product fetchedProduct = response.getBody();
        assertNotNull(fetchedProduct);
        assertEquals(1L, fetchedProduct.getId());
        assertEquals("Laptop", fetchedProduct.getName());

    }

    @Test
    void test_updateProduct() {
        long productId = 1L;
        Category electronics = new Category();
        electronics.setName("Electronics");

        Product product = new Product();
        product.setId(productId);
        product.setName("Updated Laptop");
        product.setPrice(3000F);
        product.setCategory(electronics);
        product.setStock(60);
        product.setDescription("Improved laptop.");
        product.setImageLink("/laptop2.png");

        when(productService.updateProduct(product)).thenReturn(ResponseEntity.ok(product));

        ResponseEntity<Product> response = productController.updateProduct(product);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());

        Product updatedProduct = response.getBody();
        assertNotNull(updatedProduct);
        assertEquals(1L, updatedProduct.getId());
        assertEquals("Updated Laptop", updatedProduct.getName());
    }

    @Test
    void test_deleteProduct() {
        long productId = 1L;
        ResponseEntity<String> response = productController.deleteProduct(productId);
        assertNull(response);
    }
}
