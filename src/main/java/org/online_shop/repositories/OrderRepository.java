package org.online_shop.repositories;

import org.online_shop.interfaces.PaymentStrategy;
import org.online_shop.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends Database {

    public boolean create(Order order) {
        String sql = "INSERT INTO orders (user_id, shipping_address_id, payment_method, status, shipping_fee) " +
                "VALUES(?,?,?,?, ?);";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, order.get_user().get_id());
            stmt.setInt(2, order.get_shippingAddress().get_id());
            stmt.setInt(3, order.get_paymentMethod().getId());
            stmt.setString(4, order.get_status());
            stmt.setFloat(5, order.get_shippingFee());
            return stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public Order read(Integer id, User user, ShippingAddress shippingAddress) {

//        String sql = "SELECT * FROM orders WHERE id = ? AND user_id = ? AND shipping_address_id = ?;";
//
//        try {
//            PreparedStatement stmt = conn().prepareStatement(sql);
//            stmt.setInt(1, id);
//            stmt.setInt(2, user.get_id());
//
//            ResultSet resultSet = stmt.executeQuery();
//            Order order = new Order();
//
//            if (resultSet.next()) {
//
//                PaymentStrategy paymentStrategy = new Card();
//                order.set_user(user);
//                order.set_shippingAddress(shippingAddress);
//                order.set_paymentMethod(resultSet.getFloat("payment_method"));
//            }
//        }
        return null;
    }

    public List<Order> readAll(User user) {
        String sql = "SELECT * FROM orders WHERE user_id = ?;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, user.get_id());

            List<Order> orders = new ArrayList<>();

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                ShippingAddress shippingAddress = new ShippingAddress();
                shippingAddress.set_id(resultSet.getInt("shipping_address_id"));
                Order order = new Order();
                order.set_user(user);
                order.set_id(resultSet.getInt("id"));
                order.set_status(resultSet.getString("status"));
                order.set_shippingFee(resultSet.getFloat("shipping_fee"));
                order.set_shippingAddress(shippingAddress);

                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }


    public boolean update(Order order) {

        String sql = "UPDATE orders SET status = ? WHERE order_id = ? AND user_id = ?;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, order.get_id());
            stmt.setInt(2, order.get_user().get_id());
            return stmt.execute();

        } catch (SQLException e) {
            return false;
        }
    }
}
