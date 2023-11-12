package org.online_shop.models;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private Integer _id;
    private String _name;

    private final List<Product> products = new ArrayList<>();

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                '}';
    }
}
