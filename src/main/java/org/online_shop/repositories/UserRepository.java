package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.ProductSpec;
import org.online_shop.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository extends DatabaseInMemory {

    public boolean create(User user) {
        return this.users.add(user);
    }

    public User read(String email) {
        for (User user : this.users) {
            if (user.get_email().equals(email)) {
                return user;
            }
        }
        return new User();
    }

    public List<User> readAll() {
        return this.users;
    }

    public boolean update(User updatedUser, String email) {

        System.out.println(updatedUser);

        for (int i = 0; i  < this.users.size(); i++) {
            System.out.println(users.get(i));
            if (users.get(i).get_email().equals(email)) {
                users.get(i).set_firstname(updatedUser.get_firstname());
                users.get(i).set_lastname(updatedUser.get_firstname());
                users.get(i).set_email(updatedUser.get_email());
                return true;
            }
        }
        return false;
    }

    public boolean delete(String email) {
        User user = read(email);
        return this.users.removeIf(u -> u.equals(user));
    }

    public boolean deleteAll() {
        users = new ArrayList<>();
        return true;
    }
}
