package org.online_shop.controllers;

import org.online_shop.models.User;
import org.online_shop.views.UserView;

import java.util.Scanner;

public class UserController extends DataReader {

    private final UserView _userView = new UserView();

    public boolean getUser(String email, String password) {

        return true;
    }

    public void createUser(String firstName, String lastName, String email, String password) {

    }


    public void logIn(){
        _userView.enterEmail();
        String email = readString();
        _userView.enterPassword();
        String password = readString();
    }


    public void signUp() {
        _userView.enterFirstname();
        String firstname = readString();
        _userView.enterLastname();
        String lastname = readString();
        _userView.enterEmail();
        String email = readString();
        _userView.enterPassword();
        String password = readString();

        createUser(firstname, lastname, email, password);
    }






}
