package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends DatabaseInMemory {

    public boolean create(Order order) {
        return _orders.add(order);
    }

    public Order read(int id, int userId) {

        for (Order order : _orders) {
            if (order.get_id() == id && order.get_userId() == userId)
                return order;
        }
        return new Order();
    }

    public List<Order> readAll() {
        return _orders;
    }

    public boolean update(Order order) {
        for (Order o : _orders) {
            if (o.get_id() == order.get_id()) {
                o.set_status(order.get_status());
                o.set_paymentMethod(order.get_paymentMethod());
                o.set_shippingFee(order.get_shippingFee());
                o.set_shippingAddressId(o.get_shippingAddressId());
            }
        }
        return true;
    }

    public boolean deleteAll() {
        _orders = new ArrayList<>();
        return _orders.equals(new ArrayList<>());
    }
}
