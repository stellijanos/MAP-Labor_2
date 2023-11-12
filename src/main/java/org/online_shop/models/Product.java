package org.online_shop.models;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private Integer _id;
    private String _name;
    private Float _price;
    private Category category;
    private String _description;
    private String _imageLink;
    private Integer _stock;
    private final List<ProductSpec> productSpecs = new ArrayList<>();

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public Float get_price() {
        return _price;
    }

    public void set_price(Float _price) {
        this._price = _price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_imageLink() {
        return _imageLink;
    }

    public void set_imageLink(String _imageLink) {
        this._imageLink = _imageLink;
    }

    public Integer get_stock() {
        return _stock;
    }

    public void set_stock(Integer _stock) {
        this._stock = _stock;
    }

    public List<ProductSpec> getProductSpecs() {
        return productSpecs;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _price=" + _price +
                ", category=" + category +
                ", _description='" + _description + '\'' +
                ", _imageLink='" + _imageLink + '\'' +
                ", _stock=" + _stock +
                ", productSpecs=" + productSpecs +
                '}';
    }
}
