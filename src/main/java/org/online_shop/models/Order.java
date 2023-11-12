package org.online_shop.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Integer _id;
    private User _user;
    private ShippingAddress _shippingAddress;
    private final List<OrderItem> _orderItems = new ArrayList<>();
    private String _paymentMethod;
    private String _date;
    private String _status;
    private Float _shippingFee;


    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public User get_user() {
        return _user;
    }

    public void set_user(User _user) {
        this._user = _user;
    }

    public ShippingAddress get_shippingAddress() {
        return _shippingAddress;
    }

    public void set_shippingAddress(ShippingAddress _shippingAddress) {
        this._shippingAddress = _shippingAddress;
    }

    public List<OrderItem> get_orderItems() {
        return _orderItems;
    }

    public String get_paymentMethod() {
        return _paymentMethod;
    }

    public void set_paymentMethod(String _paymentMethod) {
        this._paymentMethod = _paymentMethod;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public Float get_shippingFee() {
        return _shippingFee;
    }

    public void set_shippingFee(Float _shippingFee) {
        this._shippingFee = _shippingFee;
    }

    @Override
    public String toString() {
        return "Order{" +
                "_id=" + _id +
                ", _user=" + _user +
                ", _shippingAddress=" + _shippingAddress +
                ", _orderItems=" + _orderItems +
                ", _paymentMethod='" + _paymentMethod + '\'' +
                ", _date='" + _date + '\'' +
                ", _status='" + _status + '\'' +
                ", _shippingFee=" + _shippingFee +
                '}';
    }
}
