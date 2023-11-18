package org.online_shop.models;

import org.online_shop.interfaces.PaymentStrategy;

public class Card implements PaymentStrategy {

    private final String cardNumber;
    private final String cardName;
    private final String cvv;

    public Card(String cardNumber, String cardName, String cvv) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }

    @Override
    public void pay() {

    }

}
