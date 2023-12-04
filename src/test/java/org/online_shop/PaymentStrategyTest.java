package org.online_shop;

import org.junit.*;
import org.online_shop.interfaces.PaymentStrategy;

import static org.junit.Assert.*;

public class PaymentStrategyTest {

    private class TestPaymentStrategy implements PaymentStrategy {
        private Integer id = 123;
        private String type = "Credit";
        private String name = "John Doe";
        private String number = "1234567890123456";
        private String cvv = "123";

        @Override
        public void pay(Float amount) {
            // Implement pay method logic for testing
        }

        @Override
        public Integer getId() {
            return id;
        }
    }

    @Test
    public void test_getId() {
        PaymentStrategy paymentStrategy = new TestPaymentStrategy();
        Integer expectedId = 123;
        assertEquals(expectedId, paymentStrategy.getId());
    }
    
}
