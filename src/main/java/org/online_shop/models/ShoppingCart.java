package org.online_shop.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private Integer _id;
    private User user;
    private final List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "_id=" + _id +
                ", user=" + user +
                ", shoppingCartItems=" + shoppingCartItems +
                '}';
    }
}
