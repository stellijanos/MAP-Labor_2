package org.online_shop.models;

import org.online_shop.interfaces.PaymentStrategy;

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
