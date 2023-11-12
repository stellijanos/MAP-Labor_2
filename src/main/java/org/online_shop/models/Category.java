package org.online_shop.models;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private Integer _id;
    private String _name;
    private final List<Product> _products = new ArrayList<>();

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer id) {
        this._id = id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public List<Product> get_products() {
        return _products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + _id +
                ", name='" + _name + '\'' +
                ", products=" + _products +
                '}';
    }
}
