package org.online_shop.controllers;

import org.online_shop.enums.Response;
import org.online_shop.models.Product;
import org.online_shop.repositories.ProductRepository;

import java.util.List;

public class ProductController {

    private final ProductRepository _productRepository;

    public ProductController(ProductRepository productRepository) {
        _productRepository = productRepository;
    }

    public Response createProduct(String name, Float price, Integer categoryId, String description, Integer stock) {

        Product product = new Product();

        product.set_name(name);
        product.set_price(price);
        product.set_categoryId(categoryId);
        product.set_description(description);
        product.set_stock(stock);
        product.set_id(_productRepository.readAll().size() + 1);
        product.set_imageLink(generateImageLink(name));

        return _productRepository.create(product) ? Response.PRODUCT_CREATED_SUCCESSFULLY : Response.SOMETHING_WENT_WRONG;
    }

    public Product readProduct(Integer id) {
        return _productRepository.read(id);
    }

    public List<Product> readAll() {
        return _productRepository.readAll();
    }

    public Response update(String name, String price_str, String description, String stock_str, String categoryId_str, Integer id) {

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
        updatedProduct.set_categoryId(categoryId_str.isEmpty() ? currentProduct.get_categoryId() : Integer.parseInt(stock_str));

        return _productRepository.update(updatedProduct) ? Response.PRODUCT_UPDATED_SUCCESSFULLY : Response.SOMETHING_WENT_WRONG;
    }

    public Response deleteProduct(Integer id) {
        return _productRepository.delete(id) ? Response.PRODUCT_DELETED_SUCCESSFULLY : Response.SOMETHING_WENT_WRONG;
    }

    public String generateImageLink(String name) {
        return name + CustomControllerTools.getCurrentDateTIme();
    }

}
