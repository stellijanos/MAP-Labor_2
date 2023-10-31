package org.online_shop.models;

public class ProductSpec {
    private int _id;
    private int _productId;
    private String _specName;
    private String _specValue;

    public ProductSpec(int _id, int _productId, String _specName, String _specValue) {
        this._id = _id;
        this._productId = _productId;
        this._specName = _specName;
        this._specValue = _specValue;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_productId() {
        return _productId;
    }

    public void set_productId(int _productId) {
        this._productId = _productId;
    }

    public String get_specName() {
        return _specName;
    }

    public void set_specName(String _specName) {
        this._specName = _specName;
    }

    public String get_specValue() {
        return _specValue;
    }

    public void set_specValue(String _specValue) {
        this._specValue = _specValue;
    }

    @Override
    public String toString() {
        return "ProductSpec{" +
                "_id=" + _id +
                ", _productId=" + _productId +
                ", _specName='" + _specName + '\'' +
                ", _specValue='" + _specValue + '\'' +
                '}';
    }
}
