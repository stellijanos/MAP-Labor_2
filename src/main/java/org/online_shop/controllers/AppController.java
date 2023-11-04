package org.online_shop.controllers;


import java.lang.Thread;

import org.online_shop.repositories.UserRepository;
import org.online_shop.views.AppView;
import org.online_shop.views.UserView;


public class AppController extends Controller {

    private final AppView _appView = new AppView();
    private final UserController _userController = new UserController(new UserView(), new UserRepository());
    private  Response _session = Response.SESSION_DESTROY;

    private String _sessionId = null;

    private void sleep(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void logIn() {
        _appView.userView.enterEmail();
        String email = readString();
        _appView.userView.enterPassword();
        String password = readString();

        _session = _userController.logInUser(email, password);
        if (_session == Response.SESSION_START) {
            _sessionId = email;
            _appView.userView.logInSuccessful();

            _appView.userView.userPanel();

        } else {
            _appView.userView.logInFailed();
        }
    }

    public void signUp() {
        _appView.userView.enterFirstname();
        String firstname = readString();
        _appView.userView.enterLastname();
        String lastname = readString();
        _appView.userView.enterEmail();
        String email = readString();
        _appView.userView.enterPassword();
        String password = readString();
        Response response = _userController.createUser(firstname, lastname, email, password);

        switch (response) {
            case USER_EXISTS:
                _appView.userView.userExists();
                break;
            case SOMETHING_WENT_WRONG:
                _appView.userView.somethingWentWrong();
                break;
            case USER_CREATED_SUCCESSFULLY:
                _appView.userView.userCreatedSuccessfully();
        }
    }


    public void mainMenu() {
        boolean running = true;
        while (running) {
            _appView.logIn_signUp();
            Integer option = readInteger();

            if (option == 0)
                running = false;
            else if (option == 1)
                logIn();
            else if (option == 2)
                signUp();
            else
                _appView.optionNotFound();
            sleep(1000);
        }
        _appView.goodBye();
        _userController.listAllUsers();
    }
}
