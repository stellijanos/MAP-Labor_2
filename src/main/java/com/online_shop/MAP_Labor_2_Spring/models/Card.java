package com.online_shop.MAP_Labor_2_Spring.models;

public class Card extends Payment {

    @Override
    public void pay() {
        System.out.println("Paid with card");
    }
}
