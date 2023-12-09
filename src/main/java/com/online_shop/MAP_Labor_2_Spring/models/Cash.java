package com.online_shop.MAP_Labor_2_Spring.models;

import com.online_shop.MAP_Labor_2_Spring.interfaces.PaymentStrategy;

public class Cash implements PaymentStrategy {

    private Integer id;

    private final String type = "card";

    @Override
    public void pay(Float amount) {

    }

    @Override
    public Integer getId() {
        return null;
    }
}
