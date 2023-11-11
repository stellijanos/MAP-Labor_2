package org.online_shop.models;

public class OrderItem {

    private Integer _orderId;
    private Integer _productId;
    private float _price;
    private Integer _quantity;

    public OrderItem(Integer _orderId, Integer _productId, float _price, Integer _quantity) {
        this._orderId = _orderId;
        this._productId = _productId;
        this._price = _price;
        this._quantity = _quantity;
    }

    public Integer get_orderId() {
        return _orderId;
    }

    public void set_orderId(Integer _orderId) {
        this._orderId = _orderId;
    }

    public Integer get_productId() {
        return _productId;
    }

    public void set_productId(Integer _productId) {
        this._productId = _productId;
    }

    public float get_price() {
        return _price;
    }

    public void set_price(float _price) {
        this._price = _price;
    }

    public Integer get_quantity() {
        return _quantity;
    }

    public void set_quantity(Integer _quantity) {
        this._quantity = _quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "_orderId=" + _orderId +
                ", _productId=" + _productId +
                ", _price=" + _price +
                ", _quantity=" + _quantity +
                '}';
    }
}
