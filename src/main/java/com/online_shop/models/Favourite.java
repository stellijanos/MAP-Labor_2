package com.online_shop.MAP_Labor_2_Spring.models;

public class Favourite {

    private final User user;
    private final Product product;


    public Favourite(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }


    @Override
    public String toString() {
        return "Favourite{" +
                " product=" + product +
                ", user=" + user +
                '}';
    }
}
