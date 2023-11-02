package org.online_shop.views;

import org.online_shop.models.Order;

import java.util.List;

public class OrderView {
    public void view(Order order) {
        System.out.println(order);
    }

    public void viewAll(List<Order> orders) {
        for (Order order:orders) {
            System.out.println(order);
        }
    }
}
