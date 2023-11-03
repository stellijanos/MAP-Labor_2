package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.ProductSpec;
import org.online_shop.models.User;

import java.util.List;

public class UserRepository extends DatabaseInMemory {

    public boolean create(User user) {
        user.set_id(this.users.size()+1);

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

    public boolean update(User user) {

        return true;
    }

    public boolean delete(int _id) {

        return true;
    }

    public boolean deleteAll() {

        return true;
    }
}
