package org.online_shop.views;

import org.online_shop.controllers.AppController;
import org.online_shop.controllers.UserController;

public class AppView {

//    private AppController _appController = new AppController();

    private final UserController _userController = new UserController();


    public void mainMenu() {
        System.out.println("@author Janos Stelli" +
                "Welcome to the online shop\n" +
                "Options" +
                "1. LogIn " +
                "2. SignIn");
    }

    private String getFirstname() {
        System.out.println("Enter firstname: ");
        return _userController.readString();
    }

    private String getLastname() {
        System.out.println("Enter lastname: ");
        return _userController.readString();
    }

    private String getEmail() {
        System.out.println("Enter email address: ");
        return _userController.readString();
    }

    private String getPassword() {
        System.out.println("Enter password: ");
        return _userController.readString();
    }

    public void logIn() {
        String email = getEmail();
        String password = getPassword();
        _userController.getUser(email, password);
    }

    public void createUser() {
        String firstName = getFirstname();
        String lastName = getLastname();
        String email = getEmail();
        String password = getPassword();
        _userController.createUser(firstName, lastName, email, password);
    }

}

