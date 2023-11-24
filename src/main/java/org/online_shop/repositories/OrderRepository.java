package org.online_shop.repositories;

import org.online_shop.interfaces.PaymentStrategy;
import org.online_shop.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends Database {

    UserRepository userRepository;
    ShippingAddressRepository shippingAddressRepository;

    public OrderRepository() {
        userRepository = new UserRepository();
        shippingAddressRepository = new ShippingAddressRepository();
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

    public Order read(Integer id) {

        // , User user, ShippingAddress shippingAddress
        // AND user_id = ? AND shipping_address_id = ?

        String sql = "SELECT * FROM orders WHERE id = ? ;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
//            Order order = new Order();

            if (resultSet.next()) {
                String paymentType = resultSet.getString("payment");

      //          PaymentStrategy paymentStrategy = (paymentType == "card") ? new Card() : new Cash();
              //  order.setUser(userRepository.read(resultSet.get));
//                order.set_shippingAddress(shippingAddress);
            //    order.set_paymentMethod(resultSet.getFloat("payment_method"));
            }
        } catch (SQLException e) {
//            return new Order();
        }
        return null;
    }

    public List<Order> readAll(User user) {
        String sql = "SELECT * FROM orders WHERE user_id = ?;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, user.getId());

            List<Order> orders = new ArrayList<>();

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                ShippingAddress shippingAddress = new ShippingAddress();
                shippingAddress.setId(resultSet.getInt("shipping_address_id"));
//                Order order = new Order();
//                order.setUser(user);
//                order.setId(resultSet.getInt("id"));
//                order.setStatus(resultSet.getString("status"));
//                order.setShippingFee(resultSet.getFloat("shipping_fee"));
//                order.setShippingAddress(shippingAddress);
//
//                orders.add(order);
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
