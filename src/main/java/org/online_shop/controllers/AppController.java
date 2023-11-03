package org.online_shop.controllers;


import java.lang.Thread;
import org.online_shop.repositories.AppRepository;
import org.online_shop.views.AppView;


public class AppController extends DataReader {

    private void sleep(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private final AppView _appView = new AppView();
    private final UserController _userController = new UserController();

    public void mainMenu() {
        _appView.logIn_signUp();
        Integer option = readInteger();

        switch (option) {
            case 1:
                _userController.logIn();
                break;
            case 2:
                _userController.signUp();
                break;
            default:
                _appView.optionNotFound();
                sleep(1000);
        }
        mainMenu();
    }
}
