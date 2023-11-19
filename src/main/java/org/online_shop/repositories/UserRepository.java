package org.online_shop.repositories;

import org.online_shop.models.Database;
import org.online_shop.models.User;

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
            PreparedStatement prepStmt = this.conn().prepareStatement(sql);
            prepStmt.setString(1, user.get_firstname());
            prepStmt.setString(2, user.get_lastname());
            prepStmt.setString(3, user.get_email());
            prepStmt.setString(4, user.get_password());
            return prepStmt.execute();
        } catch (SQLException e) {
            return false; // e.printStackTrace();
        }
    }

    public User read(String email) {
        String sql = "SELECT * FROM users WHERE email = ?;";
        try {
            PreparedStatement prepStmt = this.conn().prepareStatement(sql);
            prepStmt.setString(1, email);
            ResultSet resultSet = prepStmt.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.set_id(resultSet.getInt("id"));
                user.set_firstname(resultSet.getString("firstname"));
                user.set_lastname(resultSet.getString("lastname"));
                user.set_email(resultSet.getString("email"));
                user.set_password(resultSet.getString("password"));
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
                user.set_id(resultSet.getInt("id"));
                user.set_firstname(resultSet.getString("firstname"));
                user.set_lastname(resultSet.getString("lastname"));
                user.set_email(resultSet.getString("email"));
                user.set_password(resultSet.getString("password"));
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
            PreparedStatement prepStmt = this.conn().prepareStatement(sql);
            prepStmt.setString(1, updatedUser.get_firstname());
            prepStmt.setString(2, updatedUser.get_lastname());
            prepStmt.setString(3, updatedUser.get_email());
            prepStmt.setString(4, updatedUser.get_password());
            prepStmt.setString(5, email);

            return prepStmt.execute();

        } catch (SQLException e) {
            return false;
        }
    }


    public boolean delete(String email) {

        String sql = "DELETE FROM users WHERE email = ?;";
        try {
            PreparedStatement prepStmt = this.conn().prepareStatement(sql);
            prepStmt.setString(1, email);
            return prepStmt.execute();

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteAll() {
        String sql = "DELETE FROM users;";
        try {
            Statement stmt = this.conn().createStatement();
            return stmt.execute(sql);

        } catch (SQLException e) {
            return false;
        }
    }
}
