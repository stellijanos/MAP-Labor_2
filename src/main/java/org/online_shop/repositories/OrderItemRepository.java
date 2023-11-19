package org.online_shop.repositories;

import org.online_shop.models.Database;
import org.online_shop.models.Order;
import org.online_shop.models.OrderItem;
import org.online_shop.models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository extends Database {
    public boolean create(OrderItem orderItem) {
        String sql = "INSERT INTO order_items(order_id, product_id, price, quantity) VALUES(?, ?, ?, ?);";

        try {
            PreparedStatement prepStmt = this.conn().prepareStatement(sql);
            prepStmt.setInt(1, orderItem.get_order().get_id());
            prepStmt.setInt(2, orderItem.get_product().get_id());
            prepStmt.setFloat(3, orderItem.get_price());
            prepStmt.setInt(4, orderItem.get_quantity());
            return prepStmt.execute();
        } catch (SQLException e) {
            return false; // e.printStackTrace();
        }
    }


    public List<OrderItem> readAll(Integer order_id) {
        String sql = "SELECT * FORM order_items WHERE order_id = ?";
        try {

            List<OrderItem> orderItems = new ArrayList<>();

            PreparedStatement prepStmt = this.conn().prepareStatement(sql);
            prepStmt.setInt(1, order_id);

            ResultSet resultSet = prepStmt.executeQuery();

            Order order = new Order();

            while (resultSet.next()) {
                Product product = new Product();
                product.set_id(resultSet.getInt("product_id"));
                order.set_id(resultSet.getInt("order_id"));
                OrderItem orderItem = new OrderItem();
                orderItem.set_quantity(resultSet.getInt("quantity"));
                orderItem.set_price(resultSet.getFloat("price"));

                orderItem.set_order(order);
                orderItem.set_product(product);
                orderItems.add(orderItem);
            }
            return orderItems;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public boolean update(OrderItem orderItem) {
        String sql = "UPDATE order_items SET price = ?, quantity = ? WHERE order_id = ? AND product_id = ?;";

        try {
            PreparedStatement prepStmt = conn().prepareStatement(sql);
            prepStmt.setFloat(1, orderItem.get_price());
            prepStmt.setInt(2, orderItem.get_quantity());
            prepStmt.setInt(3, orderItem.get_order().get_id());
            prepStmt.setInt(4, orderItem.get_product().get_id());

            return prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Order order, Product product) {
       String sql = "DELETE FROM order_items WHERE order_id = ? AND product_id = ?;";

       try {
           PreparedStatement stmt = conn().prepareStatement(sql);
           stmt.setInt(1, order.get_id());
           stmt.setInt(2, product.get_id());
           return stmt.execute();
       } catch (SQLException e) {
           return false;
       }
    }

    public boolean deleteAll(Integer orderId) {
        String sql = "DELETE FROM order_items WHERE order_id = ?";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, orderId);
            return stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }
}
