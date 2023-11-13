package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.Product;
import org.online_shop.models.User;

import java.util.List;

public class UserRepository extends DatabaseInMemory {

    public boolean create(User user) {
        return this._users.add(user);
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
