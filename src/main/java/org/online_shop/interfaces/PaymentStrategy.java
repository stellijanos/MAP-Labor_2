package org.online_shop.interfaces;

public interface PaymentStrategy {
    Integer id = null;

    String type = null;
    String name = null;
    String number = null;
    String cvv = null;
    void pay(Float amount);

    Integer getId();

}
