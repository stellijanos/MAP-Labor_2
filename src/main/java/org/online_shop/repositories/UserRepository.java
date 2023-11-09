package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository extends DatabaseInMemory {

    public boolean create(User user) {
        return this._users.add(user);
    }

    public User read(String email) {
        for (User user : this._users) {
            if (user.get_email().equals(email)) {
                return user;
            }
        }
        return new User();
    }

    public List<User> readAll() {
        return this._users;
    }

    public boolean update(User updatedUser, String email) {

//        System.out.println(updatedUser);

        for (int i = 0; i < this._users.size(); i++) {
            if (_users.get(i).get_email().equals(email)) {
                _users.get(i).set_firstname(updatedUser.get_firstname());
                _users.get(i).set_lastname(updatedUser.get_firstname());
                _users.get(i).set_email(updatedUser.get_email());
                return true;
            }
        }
        return false;
    }

    public boolean updatePassword(String newPassword, String email) {
        for (int i = 0; i < this._users.size(); i++) {
            if (_users.get(i).get_email().equals(email)) {
                _users.get(i).set_password(newPassword);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String email) {
        User user = read(email);
        return this._users.removeIf(u -> u.equals(user));
    }

    public boolean deleteAll() {
        _users = new ArrayList<>();
        return true;
    }
}
