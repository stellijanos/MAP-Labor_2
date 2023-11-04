package org.online_shop.controllers;


import java.lang.Thread;

import org.online_shop.repositories.UserRepository;
import org.online_shop.views.AppView;
import org.online_shop.views.UserView;


public class AppController extends Controller {

    private final AppView _appView = new AppView();
    private final UserController _userController = new UserController(new UserView(), new UserRepository());
    private Response _session = Response.SESSION_DESTROY;
    private String _sessionId = null;

    private void sleep(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void logOut() {
        _session = Response.SESSION_DESTROY;
        _sessionId = null;
    }


//     while (true) {
//        try {
//            System.out.println("Enter an integer: ");
//            number = scanner.nextInt();
//            break; // break out of the loop if the input is an integer
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid input. Please enter an integer.");
//            scanner.nextLine(); // clear the input buffer
//        }
//    }

    private void userDetails() {

        _appView.userView.print_accountDetails(_userController.getUser(_sessionId));

        while (true) {
            _appView.print_back();
            if (readInteger() == 0) {
                userPanel();
            }
        }
    }

    private void updateUserInfo() {
    }

    private void changePassword() {
    }

    private void deleteAccount() {
    }

    private void favourites() {
    }

    private void shoppingCart() {
    }

    private void searchProduct() {
    }

    private void viewAllProducts() {
    }

    private void adminPanel() {
    }

    public void userPanel() {
        while (true) {
            _appView.print_userPanel();
            Integer option = readInteger();

            switch (option) {
                case 0 -> logOut();
                case 1 -> userDetails();
                case 2 -> updateUserInfo();
                case 3 -> changePassword();
                case 4 -> deleteAccount();
                case 5 -> favourites();
                case 6 -> shoppingCart();
                case 7 -> searchProduct();
                case 8 -> viewAllProducts();
                case 9 -> {
                    if (_sessionId.equals("admin@janos")) {
                        adminPanel();
                    }
                }
                default -> _appView.print_optionNotFound();
            }
            sleep(1000);
        }
    }


    public void logIn() {
        _appView.userView.print_enterEmail();
        String email = readString();
        _appView.userView.print_enterPassword();
        String password = readString();

        _session = _userController.logInUser(email, password);
        if (_session == Response.SESSION_START) {
            _sessionId = email;
            _appView.userView.print_logInSuccessful();

            sleep(500);

            userPanel();

        } else {
            _session = null;
            _appView.userView.print_logInFailed();

        }
    }

    public void signUp() {
        _appView.userView.print_enterFirstname();
        String firstname = readString();

        _appView.userView.print_enterLastname();
        String lastname = readString();

        _appView.userView.print_enterEmail();
        String email = readString();

        _appView.userView.print_enterPassword();
        String password = readString();

        Response response = _userController.createUser(firstname, lastname, email, password);

        switch (response) {
            case USER_EXISTS -> _appView.userView.print_userExists();
            case USER_CREATED_SUCCESSFULLY -> _appView.userView.print_userCreatedSuccessfully();
            case SOMETHING_WENT_WRONG -> _appView.userView.print_somethingWentWrong();
        }
    }


    public void mainMenu() {
        _userController.createAdmin();
        boolean running = true;

        while (running) {
            _appView.print_logIn_signUp();

            switch (readInteger()) {
                case 0 -> running = false;
                case 1 -> logIn();
                case 2 -> signUp();
                default -> _appView.print_optionNotFound();
            }
            sleep(1000);
        }
        _appView.print_goodBye();
        _userController.listAllUsers();
    }
}
