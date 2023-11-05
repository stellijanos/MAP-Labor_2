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
    }

    private String getStringFromConsole(Action message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                message.perform();
                return scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sleep(500);
                scanner.nextLine(); // clear the input buffer
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Integer getIntegerFromConsole(Action message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                message.perform();
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sleep(500);
                scanner.nextLine(); // clear the input buffer
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Float getFloatFromConsole(Action message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                message.perform();
                return scanner.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sleep(500);
                scanner.nextFloat(); // clear the input buffer
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


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

        String firstname = getStringFromConsole(_appView.userView::print_enterFirstname);
        String lastname = getStringFromConsole(_appView.userView::print_enterLastname);
        String email = getStringFromConsole(_appView.userView::print_enterEmail);
        String password = getStringFromConsole(_appView.userView::print_enterPassword);

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
            switch (getIntegerFromConsole(_appView::print_logIn_signUp)) {
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
