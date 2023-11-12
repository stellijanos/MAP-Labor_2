package org.online_shop.models;

public class Favourite {

    private Product _product;
    private User _user;

    public Product get_product() {
        return _product;
    }

    public void set_product(Product _product) {
        this._product = _product;
    }

    public User get_user() {
        return _user;
    }

    public void set_user(User _user) {
        this._user = _user;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "_product=" + _product +
                ", _user=" + _user +
                '}';
    }
}
