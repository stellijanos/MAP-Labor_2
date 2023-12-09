package com.online_shop.MAP_Labor_2_Spring.models.repositories;

import org.online_shop.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Database {

    public boolean create(User user) {

        String sql = "INSERT INTO users(firstname, lastname, email, password) VALUES(?, ?, ?, ?);";

        try {
            PreparedStatement stmt = this.conn().prepareStatement(sql);
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            return false; // e.printStackTrace();
        }
    }

    public User read(String email) {
        String sql = "SELECT * FROM users WHERE email = ?;";
        try {
            PreparedStatement stmt = this.conn().prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setCreatedAt(resultSet.getString("created_at"));
            }
            return user;
        } catch (SQLException e) {
            return new User();
        }
    }

    public List<User> readAll() {
        String sql = "SELECT * FROM users;";

        try {
            Statement stmt = this.conn().createStatement();
            List<User> users = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public boolean update(User updatedUser, String email) {
        String sql = "UPDATE users SET firstname=?, lastname=?, email=?, password=? WHERE email = ?;";

        try {
            PreparedStatement stmt = this.conn().prepareStatement(sql);
            stmt.setString(1, updatedUser.getFirstname());
            stmt.setString(2, updatedUser.getLastname());
            stmt.setString(3, updatedUser.getEmail());
            stmt.setString(4, updatedUser.getPassword());
            stmt.setString(5, email);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            return false;
        }
    }


    public boolean delete(String email) {

        String sql = "DELETE FROM users WHERE email = ?;";
        try {
            PreparedStatement stmt = this.conn().prepareStatement(sql);
            stmt.setString(1, email);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteAll() {
        String sql = "DELETE FROM users;";
        try {
            Statement stmt = this.conn().createStatement();
            int rows = stmt.executeUpdate(sql);
            return rows > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
