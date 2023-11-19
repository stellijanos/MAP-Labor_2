package org.online_shop.repositories;

import org.online_shop.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavouriteRepository extends Database {
    public boolean create(Favourite favourite) {
        String sql = "INSERT INTO favourites(user_id, product_id) VALUES (? ,?);";

        try {
            PreparedStatement prepStmt = conn().prepareStatement(sql);
            prepStmt.setInt(1, favourite.getUser().getId());
            prepStmt.setInt(2, favourite.getProduct().getId());
            return prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Integer> readAll(User user) {
        String sql = "SELECT product_id FROM favourites WHERE user_id = ?;";

        try {
            PreparedStatement prepStmt = conn().prepareStatement(sql);
            prepStmt.setInt(1, user.getId());
            ResultSet resultSet = prepStmt.executeQuery();
            List<Integer> favourites = new ArrayList<>();
            while (resultSet.next()) {
                favourites.add(resultSet.getInt("product_id"));
            }
            return favourites;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public boolean delete(Favourite favourite) {
        String sql = "DELETE FROM favourites WHERE user_id = ? AND product_id = ?;";
        try {
            PreparedStatement prepStmt = conn().prepareStatement(sql);
            prepStmt.setInt(1, favourite.getUser().getId());
            prepStmt.setInt(2, favourite.getProduct().getId());
            return prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }
}
