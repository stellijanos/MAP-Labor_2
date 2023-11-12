package org.online_shop.models;

import java.util.ArrayList;
import java.util.List;

public class ShippingAddress {
    private Integer _id;
    private User _user;
    private String _name;
    private String _phone;
    private String _address;
    private String _city;
    private String _zipCode;
    private final List<Order> _orders = new ArrayList<>();

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public User get_user() {
        return _user;
    }

    public void set_user(User user) {
        this._user = user;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_zipCode() {
        return _zipCode;
    }

    public void set_zipCode(String _zipCode) {
        this._zipCode = _zipCode;
    }

    public List<Order> get_orders() {
        return _orders;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "id=" + _id +
                ", user=" + _user +
                ", name='" + _name + '\'' +
                ", phone='" + _phone + '\'' +
                ", address='" + _address + '\'' +
                ", city='" + _city + '\'' +
                ", zipCode='" + _zipCode + '\'' +
                ", orders=" + _orders +
                '}';
    }
}
