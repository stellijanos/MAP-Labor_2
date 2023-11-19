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
            prepStmt.setInt(1, orderItem.getOrder().getId());
            prepStmt.setInt(2, orderItem.getProduct().getId());
            prepStmt.setFloat(3, orderItem.getPrice());
            prepStmt.setInt(4, orderItem.getQuantity());
            return prepStmt.execute();
        } catch (SQLException e) {
            return false; // e.printStackTrace();
        }
    }


    public List<OrderItem> readAll(Order order) {
        String sql = "SELECT * FORM order_items WHERE order_id = ?";
        try {
            List<OrderItem> orderItems = new ArrayList<>();
            PreparedStatement prepStmt = this.conn().prepareStatement(sql);
            prepStmt.setInt(1, order.getId());
            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("product_id"));
                order.setId(resultSet.getInt("order_id"));
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItem.setPrice(resultSet.getFloat("price"));

                orderItem.setOrder(order);
                orderItem.setProduct(product);
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
            prepStmt.setFloat(1, orderItem.getPrice());
            prepStmt.setInt(2, orderItem.getQuantity());
            prepStmt.setInt(3, orderItem.getOrder().getId());
            prepStmt.setInt(4, orderItem.getProduct().getId());

            return prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Order order, Product product) {
       String sql = "DELETE FROM order_items WHERE order_id = ? AND product_id = ?;";

       try {
           PreparedStatement stmt = conn().prepareStatement(sql);
           stmt.setInt(1, order.getId());
           stmt.setInt(2, product.getId());
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
