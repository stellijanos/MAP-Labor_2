package com.online_shop.MAP_Labor_2_Spring.models;

public class Cash extends Payment {
    @Override
    public void pay() {
        System.out.println("Paid with cash");
    }
}
