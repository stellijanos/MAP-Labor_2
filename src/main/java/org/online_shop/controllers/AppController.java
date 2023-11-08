package org.online_shop.controllers;

import java.lang.Thread;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.online_shop.models.Session;
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
    private Session _session;
//    private String _sessionId = null;

    private void sleep(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void logOut() {
        if (_session.destroy())
            mainMenu();
    }

    private <T> T readFromConsole(Action message, Class<T> type) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                message.perform();

                if (type.equals(String.class))
                    return type.cast(scanner.nextLine());
                if (type.equals(Integer.class))
                    return type.cast(scanner.nextInt());
                if (type.equals(Float.class))
                    return type.cast(scanner.nextFloat());

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid option.");
                sleep(500);
                scanner.nextLine(); // clear the input buffer
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void userDetails() {
        _userController.listAllUsers();
        _appView.userView.print_accountDetails(_userController.getUser(_session.getId()));

        while (true) {
            if (readFromConsole(_appView::print_back, Integer.class) == 0)
                userPanel();
        }
    }

    private void updateUserInfo() {
        String firstname = readFromConsole(_appView.userView::enterNewFirstname, String.class);
        String lastname = readFromConsole(_appView.userView::enterNewLastname, String.class);
        String email = readFromConsole(_appView.userView::enterNewEmail, String.class);

        Response response = _userController.updateUser(firstname, lastname, email, _session.getId());
        switch (response) {
            case USER_UPDATED_SUCCESSFULLY -> {
                _appView.userView.print_userUpdatedSuccessfully();
                if (!email.isEmpty()) {
                    _session.destroy();
                    _session = Session.getInstance();
                    _session.setId(email);
                }
            }
            case USER_EXISTS -> _appView.userView.print_userExists();
            case SOMETHING_WENT_WRONG -> _appView.userView.print_somethingWentWrong();
        }
        sleep(500);
        userPanel();
    }

    private void changePassword() {
        String currentPassword = readFromConsole(_appView.userView::print_enterPassword, String.class);
        String newPassword = readFromConsole(_appView.userView::print_enterNewPassword, String.class);
        String confirmPassword = readFromConsole(_appView.userView::print_confirmPassword, String.class);

        Response response = _userController.updateUserPassword(currentPassword, newPassword, confirmPassword, _session.getId());

        switch (response) {
            case USER_NOT_FOUND -> _appView.userView.print_userNotFound();
            case INCORRECT_PASSWORD -> _appView.userView.print_incorrectPassword();
            case PASSWORDS_DO_NOT_MATCH -> _appView.userView.print_passwordsDoNotMatch();
            case PASSWORD_UPDATED_SUCCESSFULLY -> _appView.userView.print_passwordUpdatedSuccessfully();
            case SOMETHING_WENT_WRONG -> _appView.userView.print_somethingWentWrong();
        }
        sleep(750);
    }

    private void deleteAccount() {
        String password = readFromConsole(_appView.userView::print_enterPassword, String.class);

        Response response = _userController.deleteUser(_session.getId(), password);

        switch (response) {
            case INCORRECT_EMAIL -> _appView.userView.print_incorrectEmail();
            case INCORRECT_PASSWORD -> _appView.userView.print_incorrectPassword();
            case SOMETHING_WENT_WRONG -> _appView.userView.print_somethingWentWrong();
            case USER_DELETED_SUCCESSFULLY -> {
                if (_session.destroy()) {
                    sleep(1500);
                    mainMenu();
                }
            }
        }
    }

    private void favourites() {
    }

    private void shoppingCart() {
    }

    private void orders() {

    }

    private void searchProduct() {
    }

    private void viewAllProducts() {
    }

    private void adminPanel() {
    }


    public void userPanel() {
        while (true) {

            Integer option = readFromConsole(_appView::print_userPanel, Integer.class);
            switch (option) {
                case 0 -> logOut();
                case 1 -> userDetails();
                case 2 -> updateUserInfo();
                case 3 -> changePassword();
                case 4 -> deleteAccount();
                case 5 -> favourites();
                case 6 -> shoppingCart();
                case 7 -> orders();
                case 8 -> searchProduct();
                case 9 -> viewAllProducts();
                case 10 -> {
                    if (_session.getId().equals("admin@janos")) {
                        adminPanel();
                    }
                }
                default -> _appView.print_optionNotFound();
            }
            sleep(1000);
        }
    }


    public void logIn() {
        String email = readFromConsole(_appView.userView::print_enterEmail, String.class);
        String password = readFromConsole(_appView.userView::print_enterPassword, String.class);

        Response response = _userController.logInUser(email, password);

        switch (response) {
            case LOGIN_SUCCESSFUL -> {
                _session = Session.getInstance();
                _session.setId(email);
                _appView.userView.print_logInSuccessful();

                sleep(500);
                userPanel();
            }
            case INCORRECT_EMAIL -> _appView.userView.print_incorrectEmail();
            case INCORRECT_PASSWORD -> _appView.userView.print_incorrectPassword();
        }
    }

    public void signUp() {

        String firstname = readFromConsole(_appView.userView::print_enterFirstname, String.class);
        String lastname = readFromConsole(_appView.userView::print_enterLastname, String.class);
        String email = readFromConsole(_appView.userView::print_enterEmail, String.class);
        String password = readFromConsole(_appView.userView::print_enterPassword, String.class);

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
            switch (readFromConsole(_appView::print_logIn_signUp, Integer.class)) {
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
