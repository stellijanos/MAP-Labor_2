package org.online_shop.controllers;

import java.lang.Thread;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.online_shop.enums.Response;
import org.online_shop.models.*;
import org.online_shop.repositories.*;
import org.online_shop.views.AppView;


public class AppController {

    private final AppView _appView = new AppView();

    private final CategoryController categoryController = new CategoryController(new CategoryRepository());
    private final FavouriteController favouriteController = new FavouriteController(new FavouriteRepository());
    private final OrderController orderController = new OrderController(new OrderRepository());
    private final OrderItemController orderItemController = new OrderItemController(new OrderItemRepository());
    private final ProductController _productController = new ProductController(new ProductRepository());
    private final ProductSpecController _productSpecController = new ProductSpecController(new ProductSpecRepository());
    private final ShippingAddressController shippingAddressController = new ShippingAddressController(new ShippingAddressRepository());
    private final ShoppingCartController shoppingCartController = new ShoppingCartController(new ShoppingCartRepository());
    private final ShoppingCartItemController shoppingCartItemController = new ShoppingCartItemController(new ShoppingCartItemRepository());
    private final UserController _userController = new UserController(new UserRepository());
    private Session _session;
    private final Route route = new Route();


    private void sleep(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T readFromConsole(Runnable message, Class<T> type) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                message.run();

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

    public void setRoute(String path, Runnable function) {
        route.definePath(path, function);
    }

    private void getRoute(String path) {
        route.get(path);
    }

    public void quit() {
        _appView.print_goodBye();
        _userController.listAllUsers();
        System.exit(0);
    }

    public void run() {
        getRoute("/");
    }


    // Menu classes
    public void mainMenu() {
        _userController.createAdmin();

        switch (readFromConsole(_appView::mainMenu, Integer.class)) {
            case 0 -> getRoute("");
            case 1 -> getRoute("/login");
            case 2 -> getRoute("/signup");
            default -> getRoute("/404");
        }
    }

    public void userPanel() {
        switch (readFromConsole(_appView::userPanel, Integer.class)) {
            case 0 -> getRoute("/logout");
            case 1 -> getRoute("/account-settings");
            case 2 -> getRoute("/shipping-address-options");
            case 3 -> getRoute("/orders");
            case 4 -> getRoute("/favourites");
            case 5 -> getRoute("/shopping-cart");
            case 6 -> getRoute("/products");
            case 7 -> getRoute("/admin-panel");
            default -> getRoute("404");
        }
    }

    public void adminPanel() {

        if (!_session.getId().equals("admin@janos"))
            getRoute("/user-panel");

        switch (readFromConsole(_appView::adminPanel, Integer.class)) {
            case 0 -> getRoute("/user-panel");
            case 1 -> getRoute("/user-options");
            case 2 -> getRoute("/product-options");
            case 3 -> getRoute("/order-options");
            default -> getRoute("/404");
        }
    }

    public void accountSettings() {
        switch (readFromConsole(_appView::accountSettings, Integer.class)) {
            case 0 -> getRoute("/user-panel");
            case 1 -> getRoute("/account-settings/profile-details");
            case 2 -> getRoute("/account-settings/edit-profile-details");
            case 3 -> getRoute("/account-settings/change-password");
            case 4 -> getRoute("/account-settings/delete-account");
            default -> getRoute("/404");
        }
    }

    public void shippingAddressOptions() {
        switch (readFromConsole(_appView::shippingAddressOptions, Integer.class)) {
            case 0 -> getRoute("/user-panel");
            case 1 -> getRoute("/shipping-address-options/view-saved-addresses");
            case 2 -> getRoute("/shipping-address-options/add-address");
            case 3 -> getRoute("/shipping-address-options/edit-address");
            case 4 -> getRoute("/shipping-address-options/delete-address");
        }
    }

    public void orders() {
        switch (readFromConsole(_appView::orders, Integer.class)) {

        }
    }


    public void logIn() {
        String email = readFromConsole(_appView.userView::print_enterEmail, String.class);
        String password = readFromConsole(_appView.userView::print_enterPassword, String.class);

        Response response = _userController.logInUser(email, password);

        switch (response) {
            case USER_LOGIN_SUCCESSFUL -> {
                _session = Session.getInstance();
                _session.setId(email);
                _appView.userView.print_logInSuccessful();

                getRoute("/user-panel");
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
            case USER_CREATE_SUCCESSFUL -> _appView.userView.print_userCreatedSuccessfully();
            case SOMETHING_WENT_WRONG -> _appView.userView.print_somethingWentWrong();
        }
    }

    public void logOut() {
        if (_session.destroy())
            getRoute("/");
    }


    public void userDetails() {
        _userController.listAllUsers();
        _appView.userView.print_accountDetails(_userController.getUser(_session.getId()));

        while (true) {
            if (readFromConsole(_appView::print_back, Integer.class) == 0)
                userPanel();
        }
    }

    public void updateUserInfo() {
        String firstname = readFromConsole(_appView.userView::enterNewFirstname, String.class);
        String lastname = readFromConsole(_appView.userView::enterNewLastname, String.class);
        String email = readFromConsole(_appView.userView::enterNewEmail, String.class);

        Response response = _userController.updateUser(firstname, lastname, email, _session.getId());
        switch (response) {
            case USER_UPDATE_SUCCESSFUL -> {
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

    public void changePassword() {
        String currentPassword = readFromConsole(_appView.userView::print_enterPassword, String.class);
        String newPassword = readFromConsole(_appView.userView::print_enterNewPassword, String.class);
        String confirmPassword = readFromConsole(_appView.userView::print_confirmPassword, String.class);

        Response response = _userController.updateUserPassword(currentPassword, newPassword, confirmPassword, _session.getId());

        switch (response) {
            case USER_NOT_FOUND -> _appView.userView.print_userNotFound();
            case INCORRECT_PASSWORD -> _appView.userView.print_incorrectPassword();
            case PASSWORDS_DO_NOT_MATCH -> _appView.userView.print_passwordsDoNotMatch();
            case PASSWORD_UPDATE_SUCCESSFUL -> _appView.userView.print_passwordUpdatedSuccessfully();
            case SOMETHING_WENT_WRONG -> _appView.userView.print_somethingWentWrong();
        }
        sleep(750);
    }

    public void deleteAccount() {
        String password = readFromConsole(_appView.userView::print_enterPassword, String.class);

        Response response = _userController.deleteUser(_session.getId(), password);

        switch (response) {
            case INCORRECT_EMAIL -> _appView.userView.print_incorrectEmail();
            case INCORRECT_PASSWORD -> _appView.userView.print_incorrectPassword();
            case SOMETHING_WENT_WRONG -> _appView.userView.print_somethingWentWrong();
            case USER_DELETE_SUCCESSFUL -> {
                if (_session.destroy()) {
                    sleep(1500);
                    mainMenu();
                }
            }
        }
    }

    public void favourites() {
    }

    public void shoppingCart() {
    }

    public void searchProduct() {
    }

    public void viewAllProducts() {
    }

    public void viewAllUsers() {

    }

    public void removeAllUsers() {

    }

    public void removeUser() {

    }

    public void addProduct() {

    }


    public void updateProduct() {

    }


    public void deleteProduct() {

    }


    public void viewAllOrders() {

    }


}
