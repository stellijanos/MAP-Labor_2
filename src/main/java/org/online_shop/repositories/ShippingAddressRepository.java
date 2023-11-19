package org.online_shop.repositories;

import org.online_shop.models.Database;
import org.online_shop.models.ShippingAddress;
import org.online_shop.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShippingAddressRepository extends Database {
    public boolean create(ShippingAddress shippingAddress) {
        String sql = "INSERT INTO shipping_addresses (user_id, name, phone, address, city, zipcode) " +
                "VALUES(?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, shippingAddress.getUser().getId());
            stmt.setString(2, shippingAddress.getName());
            stmt.setString(3, shippingAddress.getPhone());
            stmt.setString(4, shippingAddress.getAddress());
            stmt.setString(5, shippingAddress.getCity());
            stmt.setString(6, shippingAddress.getZipCode());

            return stmt.execute();

        } catch (SQLException e) {
            return false;
        }
    }

    public ShippingAddress read(Integer id, User user) {

        String sql = "SELECT * FROM shipping_addresses WHERE id = ? AND user_id = ?;";

        try {

            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, user.getId());

            ResultSet resultSet = stmt.executeQuery();

            ShippingAddress shippingAddress = new ShippingAddress();

            if (resultSet.next()) {
                shippingAddress.setUser(user);
                shippingAddress.setId(resultSet.getInt("id"));
                shippingAddress.setName(resultSet.getString("name"));
                shippingAddress.setPhone(resultSet.getString("phone"));
                shippingAddress.setAddress(resultSet.getString("address"));
                shippingAddress.setCity(resultSet.getString("city"));
                shippingAddress.setZipCode(resultSet.getString("zipcode"));
            }
            return shippingAddress;

        } catch (SQLException e) {
            return new ShippingAddress();
        }

    }

    public List<ShippingAddress> readAll(User user) {

        String sql = "SELECT * FROM shipping_addresses WHERE user_id = ?;";

        try {

            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, user.getId());
            ResultSet resultSet = stmt.executeQuery();
            List<ShippingAddress> shippingAddresses = new ArrayList<>();

            while (resultSet.next()) {
                ShippingAddress shippingAddress = new ShippingAddress();
                shippingAddress.setUser(user);
                shippingAddress.setId(resultSet.getInt("id"));
                shippingAddress.setName(resultSet.getString("name"));
                shippingAddress.setPhone(resultSet.getString("phone"));
                shippingAddress.setAddress(resultSet.getString("address"));
                shippingAddress.setCity(resultSet.getString("city"));
                shippingAddress.setZipCode(resultSet.getString("zipcode"));

                shippingAddresses.add(shippingAddress);
            }
            return shippingAddresses;

        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public boolean update(ShippingAddress shippingAddress) {

        String sql = "UPDATE shipping_addresses SET name=?, phone=?, address=?, city=?, zipcode=? WHERE id = ?;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setString(1, shippingAddress.getName());
            stmt.setString(2, shippingAddress.getPhone());
            stmt.setString(3, shippingAddress.getAddress());
            stmt.setString(4, shippingAddress.getCity());
            stmt.setString(5, shippingAddress.getZipCode());
            stmt.setInt(6, shippingAddress.getId());

            return stmt.execute();

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(ShippingAddress shippingAddress) {
        String sql = "DELETE FROM shipping_addresses WHERE id = ? AND user_id = ?;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, shippingAddress.getId());
            stmt.setInt(2, shippingAddress.getUser().getId());
            return stmt.execute();

        } catch (SQLException e) {
            return false;
        }
    }

}
