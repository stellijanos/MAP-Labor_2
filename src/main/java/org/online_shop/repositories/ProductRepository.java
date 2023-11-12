package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.Product;

import java.util.List;

public class ProductRepository extends DatabaseInMemory {
    public boolean create(Product product) {
        return this._products.add(product);
    }

    public Product read(int id) {
        return _products.stream()
                .filter(product -> product.get_id().equals(id))
                .findFirst()
                .orElse(new Product());
    }

    public List<Product> readAll() {
        return this._products;
    }

    public boolean update(Product updatedProduct) {
        return _products.stream()
                .filter(p -> p.get_id().equals(updatedProduct.get_id()))
                .findFirst()
                .map(product -> {
                    product.set_name(updatedProduct.get_name());
                    product.set_price(updatedProduct.get_price());
                    product.set_description(updatedProduct.get_description());
                    product.set_category(updatedProduct.get_category());
                    product.set_imageLink(updatedProduct.get_imageLink());
                    return true;
                }).orElse(false);
    }

    public boolean delete(int id) {
        return this._products.removeIf(p -> p.get_id().equals(id));
    }

    public boolean deleteAll() {
        _products.clear();
        return true;
    }
}
