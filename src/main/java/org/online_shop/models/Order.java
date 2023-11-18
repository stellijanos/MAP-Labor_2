package org.online_shop.models;

import org.online_shop.interfaces.UserObserver;

import java.util.ArrayList;
import java.util.List;

public class Order implements UserObserver {
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

    public void set_id(Integer id) {
        this._id = id;
    }

    public User get_user() {
        return _user;
    }

    public void set_user(User user) {
        this._user = user;
    }

    public ShippingAddress get_shippingAddress() {
        return _shippingAddress;
    }

    public void set_shippingAddress(ShippingAddress shippingAddress) {
        this._shippingAddress = shippingAddress;
    }

    public List<OrderItem> get_orderItems() {
        return _orderItems;
    }

    public String get_paymentMethod() {
        return _paymentMethod;
    }

    public void set_paymentMethod(String paymentMethod) {
        this._paymentMethod = paymentMethod;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String date) {
        this._date = date;
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
                "id=" + _id +
                ", user=" + _user +
                ", shippingAddress=" + _shippingAddress +
                ", orderItems=" + _orderItems +
                ", paymentMethod='" + _paymentMethod + '\'' +
                ", date='" + _date + '\'' +
                ", status='" + _status + '\'' +
                ", shippingFee=" + _shippingFee +
                '}';
    }

    @Override
    public void update(String firstname, String lastname, String email, String password, ShoppingCart shoppingCart) {
        _user.set_firstname(firstname);
        _user.set_lastname(lastname);
        _user.set_email(email);
        _user.set_password(password);
        _user.set_shoppingCart(shoppingCart);
    }
}
