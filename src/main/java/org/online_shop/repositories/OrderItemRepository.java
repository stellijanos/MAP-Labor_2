package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.Order;
import org.online_shop.models.OrderItem;
import org.online_shop.models.Product;

import java.util.List;
import java.util.stream.Collectors;

public class OrderItemRepository extends DatabaseInMemory {
    public boolean create(OrderItem orderItem) {
        return _orderItems.add(orderItem);
    }


    public List<OrderItem> readAll(Order order) {
        return _orderItems.stream().filter(item -> item.get_order().equals(order)).collect(Collectors.toList());
    }

    public boolean update(OrderItem orderItem) {
        return _orderItems.stream()
                .filter(item -> item.get_order().equals(orderItem.get_order()) &&
                        item.get_product().equals(orderItem.get_product()))
                .findFirst()
                .map(item -> {
                    item.set_quantity(item.get_quantity());
                    return true;
                }).orElse(false);
    }

    public boolean delete(Order order, Product product) {
        return _orderItems.removeIf(orderItem -> orderItem.get_order().equals(order) && orderItem.get_product().equals(product));
    }

    public boolean deleteAll() {
        _orderItems.clear();
        return true;
    }
}
