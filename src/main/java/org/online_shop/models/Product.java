package org.online_shop.models;

public class Product {
    private int _id;
    private String _name;
    private float _price;
    private int _categoryId;
    private String _description;
    private String _imageLink;
    private int _stock;

//    public Product(int _id, String _name, float _price, int _categoryId, String _description, String _imageLink, int _stock) {
//        this._id = _id;
//        this._name = _name;
//        this._price = _price;
//        this._categoryId = _categoryId;
//        this._description = _description;
//        this._imageLink = _imageLink;
//        this._stock = _stock;
//    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public float get_price() {
        return _price;
    }

    public void set_price(float _price) {
        this._price = _price;
    }

    public int get_categoryId() {
        return _categoryId;
    }

    public void set_categoryId(int _categoryId) {
        this._categoryId = _categoryId;
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

    public int get_stock() {
        return _stock;
    }

    public void set_stock(int _stock) {
        this._stock = _stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _price=" + _price +
                ", _categoryId=" + _categoryId +
                ", _description='" + _description + '\'' +
                ", _imageLink='" + _imageLink + '\'' +
                ", _stock=" + _stock +
                '}';
    }
}
