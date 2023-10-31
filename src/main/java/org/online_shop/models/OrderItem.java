package org.online_shop.models;

public class OrderItem {

    private int _orderId;
    private int _productId;
    private float _price;
    private int _quantity;

    public OrderItem(int _orderId, int _productId, float _price, int _quantity) {
        this._orderId = _orderId;
        this._productId = _productId;
        this._price = _price;
        this._quantity = _quantity;
    }

    public int get_orderId() {
        return _orderId;
    }

    public void set_orderId(int _orderId) {
        this._orderId = _orderId;
    }

    public int get_productId() {
        return _productId;
    }

    public void set_productId(int _productId) {
        this._productId = _productId;
    }

    public float get_price() {
        return _price;
    }

    public void set_price(float _price) {
        this._price = _price;
    }

    public int get_quantity() {
        return _quantity;
    }

    public void set_quantity(int _quantity) {
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
