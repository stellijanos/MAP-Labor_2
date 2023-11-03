package org.online_shop.controllers;

import org.online_shop.models.User;
import org.online_shop.repositories.UserRepository;
import org.online_shop.views.UserView;


public class UserController extends DataReader {

    private final UserView _userView = new UserView();
    private final UserRepository _userRepository = new UserRepository();




    private boolean createUser(String firstname, String lastname, String email, String password) {
        if (logInUser(email, password)) {
            return false;
        }
        User user = new User();

        user.set_firstname(firstname);
        user.set_lastname(lastname);
        user.set_email(email);
        user.set_password(password);

        return _userRepository.create(user);
    }


    private boolean logInUser(String email, String password) {

//        _userRepository.


        return true;
    }



    public void logIn(){
        _userView.enterEmail();
        String email = readString();
        _userView.enterPassword();
        String password = readString();

        logInUser(email, password);
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

        if (createUser(firstname, lastname, email, password)) {
            _userView.userCreatedSuccessfully();
        } else {
            _userView.somethingWentWrong();
        }
    }
}
