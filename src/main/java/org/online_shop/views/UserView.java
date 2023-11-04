package org.online_shop.views;

import org.online_shop.models.User;

import java.util.List;

public class UserView {

    public void view(User user) {
        System.out.println(user);
    }

    public void viewAll(List<User> users) {
        System.out.println("Here are all the registered users:\n----------------------------\n");
        for (User user : users) {
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

    public void userExists() {
        System.out.println("User already exists");
    }

    public void incorrectPassword() {
        System.out.println("Incorrect password");
    }

    public void incorrectEmail() {
        System.out.println("Incorrect email");
    }

    public void userCreatedSuccessfully() {
        System.out.println("User created successfully!");
    }

    public void somethingWentWrong() {
        System.out.println("Something went wrong!");
    }

    public void logInSuccessful() {
        System.out.println("Login successful!");
    }

    public void logInFailed() {
        System.out.println("Login failed");
    }

    public void userPanel() {
        System.out.println("\n------------------------------\nUser Menu\n------------------------------\n" +
                "0. LogOut" +
                "1. Account details" +
                "2. Favourites" +
                "3. Shopping Cart");
    }
}
