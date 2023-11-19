package org.online_shop.repositories;

import org.online_shop.models.Database;
import org.online_shop.models.Product;
import org.online_shop.models.ShoppingCart;
import org.online_shop.models.ShoppingCartItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItemRepository extends Database {
    public boolean create(ShoppingCartItem shoppingCartItem) {
        String sql = "INSERT INTO shopping_cart_items(shopping_cart_id, product_id, quantity) VALUES(?, ?, ?);";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, shoppingCartItem.get_shoppingCart().get_id());
            stmt.setInt(2, shoppingCartItem.get_product().get_id());
            stmt.setInt(3, shoppingCartItem.get_quantity());

            return stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public List<ShoppingCartItem> readAll(ShoppingCart shoppingCart) {

        String sql = "SELECT * FROM shopping_cart_items WHERE shopping_cart_id = ?;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, shoppingCart.get_id());

            ResultSet resultSet = stmt.executeQuery();
            List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

            while (resultSet.next()) {
                Product product = new Product();
                ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                product.set_id(resultSet.getInt("product_id"));
                shoppingCartItem.set_product(product);
                shoppingCartItem.set_shoppingCart(shoppingCart);
                shoppingCartItem.set_quantity(resultSet.getInt("quantity"));

                shoppingCartItems.add(shoppingCartItem);
            }
            return shoppingCartItems;

        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public boolean update(ShoppingCartItem shoppingCartItem) {

        String sql = "UPDAE shopping_cart_items SET quantity = ? WHERE shopping_cart_id = ? AND product_id = ?";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, shoppingCartItem.get_quantity());
            stmt.setInt(2, shoppingCartItem.get_shoppingCart().get_id());
            stmt.setInt(3, shoppingCartItem.get_product().get_id());

            return stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(ShoppingCartItem shoppingCartItem) {

        String sql = "DELETE FROM shopping_cart_items WHERE shopping_cart_id = ? AND product_id = ? ;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, shoppingCartItem.get_shoppingCart().get_id());
            stmt.setInt(2, shoppingCartItem.get_product().get_id());
            return stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }
}
