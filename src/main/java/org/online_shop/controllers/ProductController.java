package org.online_shop.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.online_shop.enums.Response;
import org.online_shop.models.Category;
import org.online_shop.models.Product;
import org.online_shop.repositories.Env;
import org.online_shop.repositories.ProductRepository;

import java.util.List;
import java.util.Objects;

public class ProductController {

    private final ProductRepository _productRepository;

    public ProductController(ProductRepository productRepository) {
        _productRepository = productRepository;
    }

    public Response addProduct(String name, Float price, Category category, String description, Integer stock) {

        Product product = new Product();

        product.set_name(name);
        product.set_price(price);
        product.set_category(category);
        product.set_description(description);
        product.set_stock(stock);
        product.set_id(_productRepository.readAll().size() + 1);
        product.set_imageLink(generateImageLink(name));

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
        if (currentProduct.get_name() == null) {
            return Response.PRODUCT_NOT_FOUND;
        }

        Product updatedProduct = new Product();

        updatedProduct.set_id(id);
        updatedProduct.set_name(name.isEmpty() ? currentProduct.get_name() : name);
        updatedProduct.set_price(price_str.isEmpty() ? currentProduct.get_price() : Integer.parseInt(price_str));
        updatedProduct.set_description(description.isEmpty() ? currentProduct.get_description() : description);
        updatedProduct.set_stock(stock_str.isEmpty() ? currentProduct.get_stock() : Integer.parseInt(stock_str));
        updatedProduct.set_category(category.get_name().isEmpty() ? currentProduct.get_category() : category);

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
