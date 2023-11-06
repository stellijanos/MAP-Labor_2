package org.online_shop.views;

import org.online_shop.models.User;

import java.util.List;

public class UserView {

    public void view(User user) {
        System.out.println(user);
    }

    public void print_viewAll(List<User> users) {
        System.out.println("Here are all the registered users:\n----------------------------\n");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void print_enterFirstname() {
        System.out.println("Enter firstname: ");
    }

    public void print_enterLastname() {
        System.out.println("Enter lastname: ");
    }

    public void print_enterEmail() {
        System.out.println("Enter email address: ");
    }

    public void print_enterPassword() {
        System.out.println("Enter password: ");
    }

    public void print_userExists() {
        System.out.println("User already exists");
    }

    public void print_incorrectPassword() {
        System.out.println("Incorrect password");
    }
    public void print_incorrectEmail() {
        System.out.println("Incorrect email address!");
    }
    public void print_userCreatedSuccessfully() {
        System.out.println("User created successfully!");
    }

    public void print_somethingWentWrong() {
        System.out.println("Something went wrong!");
    }

    public void print_logInSuccessful() {
        System.out.println("Login successful!");
    }

    public void print_logInFailed() {
        System.out.println("Login failed");
    }

    public void print_accountDetails(User user) {
        System.out.printf("\n-------------------\n Account details\n-------------------\n" +
                        "Member since: %s\nFirstname:    %s\nLastname:     %s\nEmail:        %s\n",
                user.get_createdAt(), user.get_firstname(), user.get_lastname(), user.get_email());
    }

    public void print_enterNewPassword() {
        System.out.println("Enter new Password: ");
    }

    public void print_confirmPassword() {
        System.out.println("Confirm password: ");
    }

    public void print_passwordsDoNotMatch() {
        System.out.println("Passwords don't match: ");
    }


    public void print_updateUserInfo() {
        System.out.println("0. Back\n" +
                "1. Update firstname\n" +
                "2. Update lastname\n" +
                "3. Update email\n");
    }

    public void enterNewFirstname() {
        System.out.println("Enter new firstname: ");
    }

    public void enterNewLastname() {
        System.out.println("Enter new lastname");
    }

    public void enterNewEmail() {
        System.out.println("Enter new email address: ");
    }

    public void print_userUpdatedSuccessfully() {
        System.out.println("User updated successfully!");
    }

}
