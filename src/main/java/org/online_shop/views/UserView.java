package org.online_shop.views;

import org.online_shop.models.User;

import java.util.List;
import java.util.Scanner;

public class UserView {

    public void view(User user) {
        System.out.println(user);
    }

    public void viewAll(List<User> users) {
        for (User user:users) {
            System.out.println(user);
        }
    }


    public void enterFirstname() {
        System.out.println("Enter firstname: ");
    }

    public void enterLastname() {
        System.out.println("Enter lastname: ");
    }

    public void enterEmail() {
        System.out.println("Enter email address: ");
    }

    public void enterPassword() {
        System.out.println("Enter password: ");
    }


}
