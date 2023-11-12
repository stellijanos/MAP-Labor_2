package org.online_shop.models;

public class Favourite {

    private Product _product;
    private User _user;

    public Product get_product() {
        return _product;
    }

    public void set_product(Product product) {
        this._product = product;
    }

    public User get_user() {
        return _user;
    }

    public void set_user(User user) {
        this._user = user;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                " product=" + _product +
                ", user=" + _user +
                '}';
    }
}
