package org.online_shop.models;

public class OrderItem {

    private Order _order;
    private Product _product;
    private Float _price;
    private Integer _quantity;

    public Order get_order() {
        return _order;
    }

    public void set_order(Order order) {
        this._order = order;
    }

    public Product get_product() {
        return _product;
    }

    public void set_product(Product product) {
        this._product = product;
    }

    public Float get_price() {
        return _price;
    }

    public void set_price(Float _price) {
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
                "order=" + _order +
                ", product=" + _product +
                ", price=" + _price +
                ", quantity=" + _quantity +
                '}';
    }
}
