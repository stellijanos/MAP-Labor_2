package org.online_shop.models;

public class ProductSpec {
    private Integer _id;
    private Product product;
    private String _specName;
    private String _specValue;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
                ", product=" + product +
                ", _specName='" + _specName + '\'' +
                ", _specValue='" + _specValue + '\'' +
                '}';
    }
}
