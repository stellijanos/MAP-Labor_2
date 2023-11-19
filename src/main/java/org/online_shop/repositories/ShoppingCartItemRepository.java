package org.online_shop.repositories;

import org.online_shop.models.Database;
import org.online_shop.models.ShoppingCart;
import org.online_shop.models.ShoppingCartItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItemRepository extends Database {

    private final ProductRepository productRepository = new ProductRepository();

    public boolean create(ShoppingCartItem shoppingCartItem) {
        String sql = "INSERT INTO shopping_cart_items(shopping_cart_id, product_id, quantity) VALUES(?, ?, ?);";

        try (PreparedStatement stmt = conn().prepareStatement(sql)) {

            stmt.setInt(1, shoppingCartItem.getShoppingCart().getId());
            stmt.setInt(2, shoppingCartItem.getProduct().getId());
            stmt.setInt(3, shoppingCartItem.getQuantity());

            return stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public List<ShoppingCartItem> readAll(ShoppingCart shoppingCart) {

        String sql = "SELECT * FROM shopping_cart_items WHERE shopping_cart_id = ?;";

        try (PreparedStatement stmt = conn().prepareStatement(sql)) {
            stmt.setInt(1, shoppingCart.getId());

            ResultSet resultSet = stmt.executeQuery();
            List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

            while (resultSet.next()) {
                ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                shoppingCartItem.setProduct(productRepository.read(resultSet.getInt("product_id")));
                shoppingCartItem.setShoppingCart(shoppingCart);
                shoppingCartItem.setQuantity(resultSet.getInt("quantity"));

                shoppingCartItems.add(shoppingCartItem);
            }
            return shoppingCartItems;

        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public boolean update(ShoppingCartItem shoppingCartItem) {

        String sql = "UPDATE shopping_cart_items SET quantity = ? WHERE shopping_cart_id = ? AND product_id = ?";

        try (PreparedStatement stmt = conn().prepareStatement(sql)) {
            stmt.setInt(1, shoppingCartItem.getQuantity());
            stmt.setInt(2, shoppingCartItem.getShoppingCart().getId());
            stmt.setInt(3, shoppingCartItem.getProduct().getId());

            return stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(ShoppingCartItem shoppingCartItem) {

        String sql = "DELETE FROM shopping_cart_items WHERE shopping_cart_id = ? AND product_id = ? ;";

        try (PreparedStatement stmt = conn().prepareStatement(sql)) {
            stmt.setInt(1, shoppingCartItem.getShoppingCart().getId());
            stmt.setInt(2, shoppingCartItem.getProduct().getId());
            return stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }
}
