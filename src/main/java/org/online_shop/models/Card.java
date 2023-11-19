package org.online_shop.models;

import org.online_shop.interfaces.PaymentStrategy;

public class Card implements PaymentStrategy {

    private Integer id;
    private final String cardName;
    private final String number;
    private final String cvv;
    private final String type = "card";

    public Card(String number, String cardName, String cvv) {
        this.number = number;
        this.cardName = cardName;
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + number + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }

    @Override
    public void pay(Float amount) {

    }

    @Override
    public Integer getId() {
        return id;
    }

}
