package org.online_shop.models;

public class ProductSpec {
    private Integer _id;
    private Product _product;
    private String _specName;
    private String _specValue;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Product get_product() {
        return _product;
    }

    public void set_product(Product product) {
        this._product = product;
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
                "id=" + _id +
                ", product=" + _product +
                ", specName='" + _specName + '\'' +
                ", specValue='" + _specValue + '\'' +
                '}';
    }
}
