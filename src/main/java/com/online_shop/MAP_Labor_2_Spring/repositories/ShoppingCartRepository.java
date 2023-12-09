package com.online_shop.MAP_Labor_2_Spring.models.repositories;

import org.online_shop.models.Database;
import org.online_shop.models.ShoppingCart;
import org.online_shop.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingCartRepository extends Database {
    public boolean create(ShoppingCart shoppingCart) {
        String sql = "INSERT INTO shopping_carts (user_id) VALUES (?);";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, shoppingCart.getUser().getId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public ShoppingCart read(User user) {
        String sql = "SELECT * FROM shopping_carts WHERE user_id = ?";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, user.getId());
            ResultSet resultSet = stmt.executeQuery();
            ShoppingCart shoppingCart = new ShoppingCart();

            if (resultSet.next()) {
                shoppingCart.setUser(user);
                shoppingCart.setId(resultSet.getInt("id"));
            }
            return shoppingCart;

        } catch (SQLException e) {
            return new ShoppingCart();
        }
    }

    public boolean delete(ShoppingCart shoppingCart) {

        String sql = "DELETE FORM shopping_carts WHERE id = ? AND user_id = ?";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1,shoppingCart.getId());
            stmt.setInt(2,shoppingCart.getUser().getId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
