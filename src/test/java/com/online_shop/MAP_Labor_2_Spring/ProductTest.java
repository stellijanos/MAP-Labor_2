package org.online_shop;

import org.junit.*;

import static org.junit.Assert.*;

import org.online_shop.controllers.ProductController;
import org.online_shop.models.Category;
import org.online_shop.models.Product;
import org.online_shop.repositories.ProductRepository;


public class ProductTest {

    @Test
    public void test_create() {
        ProductRepository productRepository = new ProductRepository();
        ProductController productController = new ProductController(productRepository);

        Category electronics = new Category();
        electronics.setName("Electronics");

        assertEquals(0, productRepository.readAll().size());

        productController.addProduct("SmartPhone", 999.99f, electronics, "Very good phone", 50);
        assertEquals(1, productRepository.readAll().size());

    }

    @Test
    public void test_read() {
        ProductRepository productRepository = new ProductRepository();
        ProductController productController = new ProductController(productRepository);

        Category electronics = new Category();
        electronics.setName("Electronics");

        assertEquals(0, productRepository.readAll().size());

        productController.addProduct("SmartPhone", 999.99f, electronics, "Very good phone", 50);
        assertEquals(1, productRepository.readAll().size());

        Product createdProduct = productController.getProduct(1);
        assertNotNull(createdProduct);

        assertEquals("SmartPhone", createdProduct.getName());
        assertEquals((Float) 999.99f, createdProduct.getPrice());
        assertEquals(electronics, createdProduct.getCategory());
        assertEquals("Very good phone", createdProduct.getDescription());
        assertEquals((Integer) 50, createdProduct.getStock());

    }

    @Test
    public void test_update() {
        ProductRepository productRepository = new ProductRepository();
        ProductController productController = new ProductController(productRepository);

        Category electronics = new Category();
        electronics.setName("Electronics");

        assertEquals(0, productRepository.readAll().size());

        productController.addProduct("SmartPhone", 999.99f, electronics, "Very good phone", 50);
        assertEquals(1, productRepository.readAll().size());

        Product createdProduct = productController.getProduct(1);

        Category smartDevices = new Category();
        smartDevices.setName("Smart devices");

        createdProduct.setName("Telephone");
        createdProduct.setPrice(500f);
        createdProduct.setCategory(smartDevices);
        createdProduct.setDescription("It's working");
        createdProduct.setStock(10);


        assertEquals("Telephone", createdProduct.getName());
        assertEquals((Float) 500f, createdProduct.getPrice());
        assertEquals(smartDevices, createdProduct.getCategory());
        assertEquals("It's working", createdProduct.getDescription());
        assertEquals((Integer) 10, createdProduct.getStock());
    }

    @Test
    public void test_delete() {
        ProductRepository productRepository = new ProductRepository();
        ProductController productController = new ProductController(productRepository);

        Category electronics = new Category();
        electronics.setName("Electronics");

        assertEquals(0, productRepository.readAll().size());

        productController.addProduct("SmartPhone", 999.99f, electronics, "Very good phone", 50);
        assertEquals(1, productRepository.readAll().size());

        Product createdProduct = productController.getProduct(1);
        assertNotNull(createdProduct);

        productController.removeProduct(1);
        assertEquals(0, productRepository.readAll().size());
    }
}
