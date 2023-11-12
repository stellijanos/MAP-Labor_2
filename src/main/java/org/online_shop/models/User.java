package org.online_shop.models;

import org.online_shop.controllers.CustomControllerTools;

import java.util.ArrayList;
import java.util.List;


public class User {
    private Integer _id;
    private String _firstname;
    private String _lastname;
    private String _email;
    private String _password;
    private final String _createdAt;

    private final List<Favourite> _favourites = new ArrayList<>();
    private final List<Order> _orders = new ArrayList<>();
    private final List<ShippingAddress> _shippingAddresses = new ArrayList<>();
    private ShoppingCart _shoppingCart;


    public User() {
        this._createdAt = CustomControllerTools.getCurrentDateTIme();
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String get_firstname() {
        return _firstname;
    }

    public void set_firstname(String _firstname) {
        this._firstname = _firstname;
    }

    public String get_lastname() {
        return _lastname;
    }

    public void set_lastname(String _lastname) {
        this._lastname = _lastname;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_createdAt() {
        return _createdAt;
    }

    public List<Favourite> get_favourites() {
        return _favourites;
    }

    public List<Order> get_orders() {
        return _orders;
    }

    public List<ShippingAddress> get_shippingAddresses() {
        return _shippingAddresses;
    }

    public ShoppingCart get_shoppingCart() {
        return _shoppingCart;
    }

    public void set_shoppingCart(ShoppingCart shoppingCart) {
        this._shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + _id +
                ", firstname='" + _firstname + '\'' +
                ", lastname='" + _lastname + '\'' +
                ", email='" + _email + '\'' +
                ", password='" + _password + '\'' +
                ", createdAt='" + _createdAt + '\'' +
                ", favourites=" + _favourites +
                ", orders=" + _orders +
                ", shippingAddresses=" + _shippingAddresses +
                ", shoppingCart=" + _shoppingCart +
                '}';
    }
}