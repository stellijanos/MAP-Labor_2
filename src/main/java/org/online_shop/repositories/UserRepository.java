package org.online_shop.repositories;

import org.online_shop.models.Database;
import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.Product;
import org.online_shop.models.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserRepository extends DatabaseInMemory {

    public boolean create(User user) {

        return this._users.add(user);

//        String sql = "INSERT INTO users(firstname, lastname, email, password) VALUES(?,?,?,?);";
//
//        try {
//            PreparedStatement stmt = this.conn().prepareStatement(sql);
//            stmt.setString(1,user.get_firstname());
//            stmt.setString(2,user.get_lastname());
//            stmt.setString(3,user.get_email());
//            stmt.setString(4, user.get_password());
//            return stmt.execute();
//        } catch (SQLException e) {
//            return false; // e.printStackTrace();
//        }
    }

    public User read(String email) {
        return _users.stream()
                .filter(u -> u.get_email().equals(email))
                .findFirst()
                .orElse(new User());
    }

    public List<User> readAll() {
        return this._users;
    }

    public boolean update(User updatedUser, String email) {
        return _users.stream()
                .filter(user -> user.get_email().equals(email))
                .findFirst()
                .map(user -> {
                    user.set_firstname(updatedUser.get_firstname());
                    user.set_lastname(updatedUser.get_lastname());
                    user.set_email(updatedUser.get_email());
                    return true;
                }).orElse(false);
    }

    public boolean updatePassword(String newPassword, String email) {
        return _users.stream()
                .filter(user -> user.get_email().equals(email))
                .findFirst()
                .map(user -> {
                    user.set_password(newPassword);
                    return true;
                }).orElse(false);
    }

    public boolean delete(String email) {
        return this._users.removeIf(u -> u.get_email().equals(email));
    }

    public boolean deleteAll() {
        _users.clear();
        return true;
    }

}
