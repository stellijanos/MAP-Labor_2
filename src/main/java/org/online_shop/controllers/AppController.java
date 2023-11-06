package org.online_shop.controllers;

import java.lang.Thread;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.online_shop.repositories.UserRepository;
import org.online_shop.views.AppView;
import org.online_shop.views.UserView;


@FunctionalInterface
interface Action {
    void perform();
}


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

        mainMenu();
    }

    private Object readFromConsole(Action message, Class<?> dataType) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                message.perform();

                if (dataType.equals(String.class))
                    return scanner.nextLine();
                if (dataType.equals(Integer.class))
                    return scanner.nextInt();
                if (dataType.equals(Float.class))
                    return scanner.nextFloat();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sleep(500);
                scanner.nextLine(); // clear the input buffer
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void userDetails() {
        _userController.listAllUsers();
        _appView.userView.print_accountDetails(_userController.getUser(_sessionId));

        while (true) {
            if ((Integer) readFromConsole(_appView::print_back, Integer.class) == 0)
                userPanel();
        }
    }

    private void updateUserInfo() {
        String firstname = (String) readFromConsole(_appView.userView::enterNewFirstname, String.class);
        String lastname = (String) readFromConsole(_appView.userView::enterNewLastname, String.class);
        String email = (String) readFromConsole(_appView.userView::enterNewEmail, String.class);

        Response response = _userController.updateUser(firstname, lastname, email, _sessionId);
        switch (response) {
            case USER_UPDATED_SUCCESSFULLY -> {
                _appView.userView.print_userUpdatedSuccessfully();
                if (!email.isEmpty()) {
                    _sessionId = email;
                }
            }
            case USER_EXISTS -> _appView.userView.print_userExists();
            case SOMETHING_WENT_WRONG -> _appView.userView.print_somethingWentWrong();
        }
        sleep(500);
        userPanel();
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

            Integer option = (Integer) readFromConsole(_appView::print_userPanel, Integer.class);

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
        String email = (String) readFromConsole(_appView.userView::print_enterEmail, String.class);
        String password = (String) readFromConsole(_appView.userView::print_enterPassword, String.class);

        _session = _userController.logInUser(email, password);

        switch (_session) {
            case SESSION_START -> {
                _sessionId = email;
                _appView.userView.print_logInSuccessful();

                sleep(500);
                userPanel();
            }
            case INCORRECT_EMAIL -> {
                _session = Response.SESSION_DESTROY;
                _appView.userView.print_incorrectEmail();
            }
            case INCORRECT_PASSWORD -> {
                _session = Response.SESSION_DESTROY;
                _appView.userView.print_incorrectPassword();
            }
        }
    }

    public void signUp() {

        String firstname = (String) readFromConsole(_appView.userView::print_enterFirstname, String.class);
        String lastname = (String) readFromConsole(_appView.userView::print_enterLastname, String.class);
        String email = (String) readFromConsole(_appView.userView::print_enterEmail, String.class);
        String password = (String) readFromConsole(_appView.userView::print_enterPassword, String.class);

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
            switch ((Integer) readFromConsole(_appView::print_logIn_signUp, Integer.class)) {
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
