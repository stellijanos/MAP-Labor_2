package com.online_shop.MAP_Labor_2_Spring.controllers;

import org.mindrot.jbcrypt.BCrypt;
import com.online_shop.MAP_Labor_2_Spring.enums.Response;
import org.online_shop.models.Category;
import org.online_shop.models.Product;
import com.online_shop.MAP_Labor_2_Spring.models.repositories.Env;
import com.online_shop.MAP_Labor_2_Spring.models.repositories.ProductRepository;

import java.util.List;

public class ProductController {

    private final ProductRepository _productRepository;

    public ProductController(ProductRepository productRepository) {
        _productRepository = productRepository;
    }

    public Response addProduct(String name, Float price, Category category, String description, Integer stock) {

        Product product = new Product();

        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setDescription(description);
        product.setStock(stock);
        product.setId(_productRepository.readAll().size() + 1);
        product.setImageLink(generateImageLink(name));

        return _productRepository.create(product) ? Response.PRODUCT_CREATE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }

    public Product getProduct(Integer id) {
        return _productRepository.read(id);
    }

    public List<Product> getALl() {
        return _productRepository.readAll();
    }

    public Response modify(String name, String price_str, String description, Category category, String stock_str, Integer id) {

        Product currentProduct = _productRepository.read(id);
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

        return _productRepository.update(updatedProduct) ? Response.PRODUCT_UPDATE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }

    public Response removeProduct(Integer id) {
        return _productRepository.delete(id) ? Response.PRODUCT_DELETE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }

    public Response removeAllProducts(String adminPassword) {
        Env env = new Env();
        return !BCrypt.checkpw(adminPassword, env.load().get("ADMIN_PASSWORD")) ?
                Response.INCORRECT_PASSWORD : _productRepository.deleteAll() ? Response.ALL_PRODUCTS_DELETE_SUCCESSFUL : Response.SOMETHING_WENT_WRONG;
    }

    public String generateImageLink(String name) {
        return name + CustomControllerTools.getCurrentDateTIme();
    }

}
