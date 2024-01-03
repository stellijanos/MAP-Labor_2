package com.online_shop.MAP_Labor_2_Spring;

import com.online_shop.MAP_Labor_2_Spring.models.Payment;
import org.junit.*;

import static org.junit.Assert.*;

public class PaymentStrategyTest {

    private static class TestPayment extends Payment {
        private Long id = 1L;
        private String type = "cash";
        private String name = "John Doe";

        @Override
        public void pay() {
            System.out.println("Paying with test");
        }

    }

    @Test
    public void test_getId() {
        Payment paymentStrategy = new TestPayment();
        paymentStrategy.setId(1L);
        paymentStrategy.setName("John Doe");
        paymentStrategy.setAmount(123.45F);
        paymentStrategy.setType("test");

        Long expectedId = 1L;
        Float expectedAmount = 123.45F;

        assertEquals(expectedId, paymentStrategy.getId());
        assertEquals(expectedAmount, paymentStrategy.getAmount());
        assertEquals("John Doe", paymentStrategy.getName());
        assertEquals("test", paymentStrategy.getType());
    }

}
