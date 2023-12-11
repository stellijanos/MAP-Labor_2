package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.Category;
import com.online_shop.MAP_Labor_2_Spring.models.Product;
import com.online_shop.MAP_Labor_2_Spring.repositories.Env;
import com.online_shop.MAP_Labor_2_Spring.repositories.ProductRepository;
import org.mindrot.jbcrypt.BCrypt;
import com.online_shop.MAP_Labor_2_Spring.enums.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/create")
    public void createProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getALl() {
        return (List<Product>) productRepository.findAll();
    }

    public Response modify(String name, String price_str, String description, Category category, String stock_str, Integer id) {

        Product currentProduct = productRepository.findById(id).orElse(new Product());
        if (currentProduct.getName() == null) {
            return Response.PRODUCT_NOT_FOUND;
        }

        Product updatedProduct = new Product();

        updatedProduct.setId(id);
        updatedProduct.setName(name.isEmpty() ? currentProduct.getName() : name);
        updatedProduct.setPrice(price_str.isEmpty() ? currentProduct.getPrice() : Integer.parseInt(price_str));
        updatedProduct.setDescription(description.isEmpty() ? currentProduct.getDescription() : description);
        updatedProduct.setStock(stock_str.isEmpty() ? currentProduct.getStock() : Integer.parseInt(stock_str));
        updatedProduct.setCategory(category.getName().isEmpty() ? currentProduct.getCategory() : category);

        productRepository.save(updatedProduct);
        return Response.PRODUCT_UPDATE_SUCCESSFUL;
    }

    @DeleteMapping("/{id}")
    public void removeProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public Response removeAllProducts(String adminPassword) {

        if (!BCrypt.checkpw(adminPassword, Env.load().get("ADMIN_PASSWORD")))
                return Response.INCORRECT_PASSWORD;
        productRepository.deleteAll();
        return Response.ALL_PRODUCTS_DELETE_SUCCESSFUL;
    }

    public String generateImageLink(String name) {
        return name + CustomControllerTools.getCurrentDateTIme();
    }

}
