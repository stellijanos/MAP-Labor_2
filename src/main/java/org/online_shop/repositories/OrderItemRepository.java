package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class OrderItemRepository extends DatabaseInMemory {
    public boolean create(OrderItem orderItem) {
        return _orderItems.add(orderItem);
    }


    public List<OrderItem> readAll(Integer orderId) {
        return _orderItems.stream().filter(item -> item.get_orderId().equals(orderId)).collect(Collectors.toList());
    }

    public boolean update(OrderItem orderItem) {
        return _orderItems.stream()
                .filter(item -> item.get_orderId().equals(orderItem.get_orderId()) &&
                        item.get_productId().equals(orderItem.get_productId()))
                .findFirst()
                .map(item -> {
                    item.set_quantity(item.get_quantity());
                    return true;
                }).orElse(false);
    }

    public boolean delete(Integer orderId, Integer productId) {
        return _orderItems.removeIf(orderItem -> orderItem.get_orderId().equals(orderId) && orderItem.get_productId().equals(productId));
    }

    public boolean deleteAll() {
        _orderItems.clear();
        return true;
    }
}
