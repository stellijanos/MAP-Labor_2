package org.online_shop.views;

import org.online_shop.models.OrderItem;

import java.util.List;

public class OrderItemView {
    public void view(OrderItem orderItem) {
        System.out.println(orderItem);
    }

    public void viewAll(List<OrderItem> orderItems) {
        for (OrderItem orderItem:orderItems) {
            System.out.println(orderItem);
        }
    }
}
