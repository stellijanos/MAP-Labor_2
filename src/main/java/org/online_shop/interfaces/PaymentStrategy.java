package org.online_shop.interfaces;

public interface PaymentStrategy {

    Integer id = null;
    void pay();

    Integer getId();
}
