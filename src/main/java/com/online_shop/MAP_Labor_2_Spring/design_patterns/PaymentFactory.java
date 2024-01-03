package com.online_shop.MAP_Labor_2_Spring.design_patterns;

import com.online_shop.MAP_Labor_2_Spring.models.Card;
import com.online_shop.MAP_Labor_2_Spring.models.Cash;
import com.online_shop.MAP_Labor_2_Spring.models.Payment;

public class PaymentFactory {
    public Payment createPayment(String paymentType) throws Exception {
        if (paymentType.equalsIgnoreCase("cash")) {
            return new Cash();
        }
        if (paymentType.equalsIgnoreCase("card")) {
            return new Card();
        }
        throw new Exception("Invalid Payment type");
    }
}
