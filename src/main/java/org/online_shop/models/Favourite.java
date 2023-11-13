package org.online_shop.models;

public class Favourite {

    private User _user;
    private Product _product;


    public Favourite(User _user, Product _product) {
        this._user = _user;
        this._product = _product;
    }

    public Product get_product() {
        return _product;
    }

    public User get_user() {
        return _user;
    }


    public boolean addToFavourites() {
        return notifyObserver();
    }

    public boolean notifyObserver() {
        return _user.addToFavourites(_product);
    }


    @Override
    public String toString() {
        return "Favourite{" +
                " product=" + _product +
                ", user=" + _user +
                '}';
    }
}
