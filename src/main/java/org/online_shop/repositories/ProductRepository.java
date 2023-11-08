package org.online_shop.repositories;

import org.online_shop.models.Category;
import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends DatabaseInMemory {
    public boolean create(Product product) {
        return this.products.add(product);
    }

    public Product read(int id) {

        for (Product product : this.products) {
            if (product.get_id() == id)
                return product;
        }
        return new Product();
    }

    public List<Product> readAll() {
        return this.products;
    }

    public boolean update(Product updatedProduct, int id) {

        for (int i=0; i<this.products.size(); i++) {
            if (products.get(i).get_id() == id) {
                products.get(i).set_name(updatedProduct.get_name());
                products.get(i).set_price(updatedProduct.get_price());
                products.get(i).set_description(updatedProduct.get_description());
                products.get(i).set_categoryId(updatedProduct.get_categoryId());
                products.get(i).set_imageLink(updatedProduct.get_imageLink());
                return true;
            }
        }

        return false;
    }

    public boolean delete(int id) {
        Product product = read(id);
        return this.products.removeIf(p -> p.equals(product));
    }

    public boolean deleteAll() {
        products = new ArrayList<>();
        return true;
    }
}
