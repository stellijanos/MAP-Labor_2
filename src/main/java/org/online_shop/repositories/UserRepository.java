package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.ProductSpec;
import org.online_shop.models.User;

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

    public boolean update(User user, int _id) {

        return true;
    }

    public boolean delete(String email) {
        User user = read(email);
        return this.users.removeIf(u -> u.equals(user));
    }

    public boolean deleteAll() {

        return true;
    }
}
