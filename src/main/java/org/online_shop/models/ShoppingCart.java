package org.online_shop.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private Integer _id;
    private User _user;
    private final List<ShoppingCartItem> _shoppingCartItems = new ArrayList<>();

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

    public List<ShoppingCartItem> get_shoppingCartItems() {
        return _shoppingCartItems;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + _id +
                ", user=" + _user +
                ", shoppingCartItems=" + _shoppingCartItems +
                '}';
    }
}
