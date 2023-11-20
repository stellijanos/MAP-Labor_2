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

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderItemRepository() {
        productRepository = new ProductRepository();
        orderRepository = new OrderRepository();
    }

    public boolean create(OrderItem orderItem) {
        String sql = "INSERT INTO order_items(order_id, product_id, price, quantity) VALUES(?, ?, ?, ?);";

        try {
            PreparedStatement stmt = this.conn().prepareStatement(sql);
            stmt.setInt(1, orderItem.getOrder().getId());
            stmt.setInt(2, orderItem.getProduct().getId());
            stmt.setFloat(3, orderItem.getPrice());
            stmt.setInt(4, orderItem.getQuantity());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            return false; // e.printStackTrace();
        }
    }


    public List<OrderItem> readAll(Order order) {
        String sql = "SELECT * FORM order_items WHERE order_id = ?";
        try {
            List<OrderItem> orderItems = new ArrayList<>();
            PreparedStatement stmt = this.conn().prepareStatement(sql);
            stmt.setInt(1, order.getId());
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                order.setId(resultSet.getInt("id"));
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItem.setPrice(resultSet.getFloat("price"));

                orderItem.setOrder(orderRepository.read(resultSet.getInt("order_id")));
                orderItem.setProduct(productRepository.read(resultSet.getInt("product_id")));
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
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setFloat(1, orderItem.getPrice());
            stmt.setInt(2, orderItem.getQuantity());
            stmt.setInt(3, orderItem.getOrder().getId());
            stmt.setInt(4, orderItem.getProduct().getId());

            int rows = stmt.executeUpdate();
            return rows > 0;
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
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteAll(Order order) {
        String sql = "DELETE FROM order_items WHERE order_id = ?";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, order.getId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
