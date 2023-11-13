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

    private List<Product> _favourites = new ArrayList<>();
    private List<Order> _orders = new ArrayList<>();
    private List<ShippingAddress> _shippingAddresses = new ArrayList<>();
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

    public List<Product> get_favourites() {
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

    public void set_favourites(List<Product> _favourites) {
        this._favourites = _favourites;
    }

    public void set_orders(List<Order> _orders) {
        this._orders = _orders;
    }

    public void set_shippingAddresses(List<ShippingAddress> _shippingAddresses) {
        this._shippingAddresses = _shippingAddresses;
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


    public boolean addToFavourites(Product product) {
        return _favourites.add(product);
    }

}