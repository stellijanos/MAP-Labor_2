package org.online_shop.models;

public class Favourite {

    private Product _product;

    private User _user;

    public Favourite(User user, Product product) {
        _user = user;
        _product = product;
    }


    @Override
    public String toString() {
        return "Favourite{" +
                "_user=" + _user.get_email() +
                ", _product=" + _product.get_name() +
                '}';
    }
}
