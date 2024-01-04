package com.online_shop.MAP_Labor_2_Spring.design_patterns;

import com.online_shop.MAP_Labor_2_Spring.models.Order;
import com.online_shop.MAP_Labor_2_Spring.models.OrderItem;
import lombok.Getter;

import java.util.List;

public class OrderDecorator {

    private final Order order;

    @Getter
    private Float subTotal = 0F;

    private Float shipping_fee = 15F;

    public OrderDecorator(Order order) {
        this.order = order;
    }

    public Order getOrder() {

        order.setShippingFee(calculateShippingFee());
        return order;
    }

    private Float calculateShippingFee() {
        List<OrderItem> items = (List<OrderItem>) order.getOrderItems();

        subTotal = (float) items.stream().mapToDouble(OrderItem::getPrice).sum();
        if (subTotal > 1000) shipping_fee = 0F;
        return shipping_fee;
    }
}
