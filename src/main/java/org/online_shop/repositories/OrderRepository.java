package org.online_shop.repositories;

import org.online_shop.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends Database {

    UserRepository userRepository;
    ShippingAddressRepository shippingAddressRepository;
    PaymentRepository paymentRepository;

    public OrderRepository() {
        userRepository = new UserRepository();
        shippingAddressRepository = new ShippingAddressRepository();
        paymentRepository = new PaymentRepository();
    }

    public boolean create(Order order) {
        String sql = "INSERT INTO orders (user_id, shipping_address_id, payment_method, status, shipping_fee) " +
                "VALUES(?,?,?,?, ?);";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, order.getUser().getId());
            stmt.setInt(2, order.getShippingAddress().getId());
            stmt.setInt(3, order.getPaymentMethod().getId());
            stmt.setString(4, order.getStatus());
            stmt.setFloat(5, order.getShippingFee());
            return !stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public Order read(Integer id, User user) {

        String sql = "SELECT * FROM orders WHERE id = ? and user_id = ?;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, user.getId());

            ResultSet resultSet = stmt.executeQuery();

            Order order = new Order();

            if (resultSet.next()) {
                order.setUser(user);
                order.setShippingAddress(shippingAddressRepository.read(resultSet.getInt("shipping_address_id"), user));
                order.setId(resultSet.getInt("id"));
                order.setStatus(resultSet.getString("status"));
                order.setShippingFee(resultSet.getFloat("shipping_fee"));
                order.setPaymentMethod(paymentRepository.read(resultSet.getInt("payment_id")));
            }

        } catch (SQLException e) {
            return new Order();
        }
        return null;
    }

//    public Order readAll() {
//        String sql = "SELECT * FROM orders";
//
//        try {
//            Statement stmt = conn().createStatement();
//            ResultSet resultSet = stmt.executeQuery(sql);
//
//            List<Order> orders = new ArrayList<>();
//
//            while (resultSet.next()) {
//                Order order = new Order();
//
//                order.setUser(user);
//                order.setShippingAddress(shippingAddressRepository.read(resultSet.getInt("shipping_address_id"), user));
//                order.setId(resultSet.getInt("id"));
//                order.setStatus(resultSet.getString("status"));
//                order.setShippingFee(resultSet.getFloat("shipping_fee"));
//                order.setPaymentMethod(paymentRepository.read(resultSet.getInt("payment_id")));
//
//                orders.add(order);
//            }
//            return orders;
//        } catch (SQLException e) {
//            return new ArrayList<>();
//        }
//    }

    public List<Order> readAll(User user) {
        String sql = "SELECT * FROM orders WHERE user_id = ?;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, user.getId());
            ResultSet resultSet = stmt.executeQuery();

            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                Order order = new Order();

                order.setUser(user);
                order.setShippingAddress(shippingAddressRepository.read(resultSet.getInt("shipping_address_id"), user));
                order.setId(resultSet.getInt("id"));
                order.setStatus(resultSet.getString("status"));
                order.setShippingFee(resultSet.getFloat("shipping_fee"));
                order.setPaymentMethod(paymentRepository.read(resultSet.getInt("payment_id")));

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
            stmt.setInt(1, order.getId());
            stmt.setInt(2, order.getUser().getId());
            return !stmt.execute();

        } catch (SQLException e) {
            return false;
        }
    }
}
