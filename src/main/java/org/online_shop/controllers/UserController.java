package org.online_shop.controllers;

import org.online_shop.models.User;
import org.online_shop.repositories.UserRepository;
import org.online_shop.views.UserView;


public class UserController extends DataReader {

    private final UserView _userView = new UserView();
    private final UserRepository _userRepository = new UserRepository();


    private void createUser(String firstname, String lastname, String email, String password) {
        if (logInUser(email, password) != null) {
            _userView.userExists();
            return;
        }
        User user = new User();
        user.set_firstname(firstname);
        user.set_lastname(lastname);
        user.set_email(email);
        user.set_password(String.valueOf(password.hashCode()));

        if (_userRepository.create(user)) {
            _userView.userCreatedSuccessfully();
        } else {
            _userView.somethingWentWrong();
        }
    }


    private String logInUser(String email, String password) {
        User user = _userRepository.read(email);
        return user.get_email();
    }

    public String logIn() {
        _userView.enterEmail();
        String email = readString();
        _userView.enterPassword();
        String password = readString();

        return logInUser(email, password);
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

    public void listAllUsers() {
        _userView.viewAll(_userRepository.users);
    }

    public Object logout() {
        return null;
    }
}
