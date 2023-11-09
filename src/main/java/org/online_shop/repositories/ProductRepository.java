package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends DatabaseInMemory {
    public boolean create(Product product) {
        return this._products.add(product);
    }

    public Product read(int id) {

        for (Product product : this._products) {
            if (product.get_id() == id)
                return product;
        }
        return new Product();
    }

    public List<Product> readAll() {
        return this._products;
    }

    public boolean update(Product updatedProduct) {

        for (int i = 0; i<this._products.size(); i++) {
            if (_products.get(i).get_id() == updatedProduct.get_id()) {
                _products.get(i).set_name(updatedProduct.get_name());
                _products.get(i).set_price(updatedProduct.get_price());
                _products.get(i).set_description(updatedProduct.get_description());
                _products.get(i).set_categoryId(updatedProduct.get_categoryId());
                _products.get(i).set_imageLink(updatedProduct.get_imageLink());
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        Product product = read(id);
        return this._products.removeIf(p -> p.equals(product));
    }

    public boolean deleteAll() {
        _products = new ArrayList<>();
        return _products.equals(new ArrayList<>());
    }
}
