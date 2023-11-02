package org.online_shop.views;

import org.online_shop.models.User;

import java.util.List;

public class UserView {

    public void view(User user) {
        System.out.println(user);
    }

    public void viewAll(List<User> users) {
        for (User user:users) {
            System.out.println(user);
        }
    }

}
