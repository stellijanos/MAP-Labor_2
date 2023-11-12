package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.Order;
import org.online_shop.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository extends DatabaseInMemory {

    public boolean create(Order order) {
        return _orders.add(order);
    }

    public Order read(Integer id, User user) {
        return _orders.stream()
                .filter(order -> order.get_id().equals(id) && order.get_user().equals(user))
                .findFirst().orElse(new Order());
    }

    public List<Order> readAll(User user) {
        return _orders.stream().filter(order -> order.get_user().equals(user)).collect(Collectors.toList());
    }

    public boolean update(Order order) {
        return _orders.stream()
                .filter(o -> o.get_id().equals(order.get_id()) && o.get_user().equals(order.get_user()))
                .findFirst()
                .map(o -> {
                    o.set_paymentMethod(order.get_paymentMethod());
                    o.set_status(order.get_status());
                    o.set_shippingFee(order.get_shippingFee());
                    return true;
                }).orElse(false);
    }

    public boolean deleteAll() {
        _orders.clear();
        return true;
    }
}
