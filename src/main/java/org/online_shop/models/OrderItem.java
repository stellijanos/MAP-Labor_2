package org.online_shop.models;

public class OrderItem {

    private Order order;
    private Product product;
    private Float _price;
    private Integer _quantity;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
                "order=" + order +
                ", product=" + product +
                ", _price=" + _price +
                ", _quantity=" + _quantity +
                '}';
    }
}
