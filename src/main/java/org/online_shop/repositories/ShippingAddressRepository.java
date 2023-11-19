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
            stmt.setInt(1, shippingAddress.get_user().get_id());
            stmt.setString(2, shippingAddress.get_name());
            stmt.setString(3, shippingAddress.get_phone());
            stmt.setString(4, shippingAddress.get_address());
            stmt.setString(5, shippingAddress.get_city());
            stmt.setString(6, shippingAddress.get_zipCode());

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
            stmt.setInt(2, user.get_id());

            ResultSet resultSet = stmt.executeQuery();

            ShippingAddress shippingAddress = new ShippingAddress();

            if (resultSet.next()) {
                shippingAddress.set_user(user);
                shippingAddress.set_id(resultSet.getInt("id"));
                shippingAddress.set_name(resultSet.getString("name"));
                shippingAddress.set_phone(resultSet.getString("phone"));
                shippingAddress.set_address(resultSet.getString("address"));
                shippingAddress.set_city(resultSet.getString("city"));
                shippingAddress.set_zipCode(resultSet.getString("zipcode"));
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
            stmt.setInt(1, user.get_id());
            ResultSet resultSet = stmt.executeQuery();
            List<ShippingAddress> shippingAddresses = new ArrayList<>();

            while (resultSet.next()) {
                ShippingAddress shippingAddress = new ShippingAddress();
                shippingAddress.set_user(user);
                shippingAddress.set_id(resultSet.getInt("id"));
                shippingAddress.set_name(resultSet.getString("name"));
                shippingAddress.set_phone(resultSet.getString("phone"));
                shippingAddress.set_address(resultSet.getString("address"));
                shippingAddress.set_city(resultSet.getString("city"));
                shippingAddress.set_zipCode(resultSet.getString("zipcode"));

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
            stmt.setString(1, shippingAddress.get_name());
            stmt.setString(2, shippingAddress.get_phone());
            stmt.setString(3, shippingAddress.get_address());
            stmt.setString(4, shippingAddress.get_city());
            stmt.setString(5, shippingAddress.get_zipCode());
            stmt.setInt(6, shippingAddress.get_id());

            return stmt.execute();

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(ShippingAddress shippingAddress) {
        String sql = "DELETE FROM shipping_addresses WHERE id = ? AND user_id = ?;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, shippingAddress.get_id());
            stmt.setInt(2, shippingAddress.get_user().get_id());
            return stmt.execute();

        } catch (SQLException e) {
            return false;
        }
    }

}
