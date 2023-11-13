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
        electronics.set_name("Electronics");

        assertEquals(0, productRepository.readAll().size());

        productController.addProduct("SmartPhone", 999.99f, electronics, "Very good phone", 50);
        assertEquals(1, productRepository.readAll().size());

    }

    @Test
    public void test_read() {
        ProductRepository productRepository = new ProductRepository();
        ProductController productController = new ProductController(productRepository);

        Category electronics = new Category();
        electronics.set_name("Electronics");

        assertEquals(0, productRepository.readAll().size());

        productController.addProduct("SmartPhone", 999.99f, electronics, "Very good phone", 50);
        assertEquals(1, productRepository.readAll().size());

        Product createdProduct = productController.getProduct(1);
        assertNotNull(createdProduct);

        assertEquals("SmartPhone", createdProduct.get_name());
        assertEquals((Float) 999.99f, createdProduct.get_price());
        assertEquals(electronics, createdProduct.get_category());
        assertEquals("Very good phone", createdProduct.get_description());
        assertEquals((Integer) 50, createdProduct.get_stock());

    }

    @Test
    public void test_update() {
        ProductRepository productRepository = new ProductRepository();
        ProductController productController = new ProductController(productRepository);

        Category electronics = new Category();
        electronics.set_name("Electronics");

        assertEquals(0, productRepository.readAll().size());

        productController.addProduct("SmartPhone", 999.99f, electronics, "Very good phone", 50);
        assertEquals(1, productRepository.readAll().size());

        Product createdProduct = productController.getProduct(1);

        Category smartDevices = new Category();
        smartDevices.set_name("Smart devices");

        createdProduct.set_name("Telephone");
        createdProduct.set_price(500f);
        createdProduct.set_category(smartDevices);
        createdProduct.set_description("It's working");
        createdProduct.set_stock(10);


        assertEquals("Telephone", createdProduct.get_name());
        assertEquals((Float) 500f, createdProduct.get_price());
        assertEquals(smartDevices, createdProduct.get_category());
        assertEquals("It's working", createdProduct.get_description());
        assertEquals((Integer) 10, createdProduct.get_stock());
    }

    @Test
    public void test_delete() {
        ProductRepository productRepository = new ProductRepository();
        ProductController productController = new ProductController(productRepository);

        Category electronics = new Category();
        electronics.set_name("Electronics");

        assertEquals(0, productRepository.readAll().size());

        productController.addProduct("SmartPhone", 999.99f, electronics, "Very good phone", 50);
        assertEquals(1, productRepository.readAll().size());

        Product createdProduct = productController.getProduct(1);
        assertNotNull(createdProduct);

        productController.removeProduct(1);
        assertEquals(0, productRepository.readAll().size());
    }
}
