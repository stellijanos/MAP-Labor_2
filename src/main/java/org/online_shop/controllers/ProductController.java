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

    public Response update(Product updatedProduct) {

        Product currentProduct = _productRepository.read(updatedProduct.get_id());
        if (currentProduct.get_name() == null) {
            return Response.PRODUCT_NOT_FOUND;
        }

        Product product = new Product();

        product.set_id(updatedProduct.get_id());
        product.set_name(updatedProduct.get_name().isEmpty() ? currentProduct.get_name() : updatedProduct.get_name());
        product.set_price(updatedProduct.get_price() == Integer.MAX_VALUE ? currentProduct.get_price() : updatedProduct.get_price());
        product.set_description(updatedProduct.get_description().isEmpty() ? currentProduct.get_description() : updatedProduct.get_description());
        product.set_stock(updatedProduct.get_stock() == Integer.MAX_VALUE ? currentProduct.get_stock() : updatedProduct.get_stock());
        product.set_categoryId(updatedProduct.get_categoryId() == Integer.MAX_VALUE ? currentProduct.get_categoryId() : updatedProduct.get_categoryId());

        return _productRepository.update(product) ? Response.PRODUCT_UPDATED_SUCCESSFULLY : Response.SOMETHING_WENT_WRONG;
    }

    public Response deleteProduct(Integer id) {
        return _productRepository.delete(id) ? Response.PRODUCT_DELETED_SUCCESSFULLY : Response.SOMETHING_WENT_WRONG;
    }


    public String generateImageLink(String name) {
        return name + CustomControllerTools.getCurrentDateTIme();
    }

}
