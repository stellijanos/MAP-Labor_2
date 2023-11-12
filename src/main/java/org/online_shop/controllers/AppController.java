package org.online_shop.controllers;

import java.lang.Thread;
import java.lang.reflect.Method;
import java.util.InputMismatchException;
import java.util.Objects;
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
        String[] url = path.split("\\?");
        String actualPath = url[0];
        String response = url.length == 1 ? "no_response" : url[1];

        try {
            if (!response.equals("no_response") && !response.isEmpty()) {
                Method method = _appView.getClass().getDeclaredMethod(response);
                method.invoke(_appView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        sleep(500);

        if (!route.get(actualPath)) {
            route.get("/");
        }
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
            default -> getRoute("/?option_not_found");
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
            default -> getRoute("/user-panel?option_not_found");
        }
    }

    public void accountSettings() {
        switch (readFromConsole(_appView::accountSettings, Integer.class)) {
            case 0 -> getRoute("/user-panel");
            case 1 -> getRoute("/account-settings/profile-details");
            case 2 -> getRoute("/account-settings/edit-profile-details");
            case 3 -> getRoute("/account-settings/change-password");
            case 4 -> getRoute("/account-settings/delete-account");
            default -> getRoute("/account-settings?option_not_found");
        }
    }

    public void shippingAddressOptions() {
        switch (readFromConsole(_appView::shippingAddressOptions, Integer.class)) {
            case 0 -> getRoute("/user-panel");
            case 1 -> getRoute("/shipping-address-options/view-saved-addresses");
            case 2 -> getRoute("/shipping-address-options/add-address");
            case 3 -> getRoute("/shipping-address-options/edit-address");
            case 4 -> getRoute("/shipping-address-options/delete-address");
            default -> getRoute("/shipping-address-options?option_not_found");
        }
    }

    public void orders() {
        switch (readFromConsole(_appView::orders, Integer.class)) {
            case 0 -> getRoute("/user-panel");
            case 1 -> getRoute("/orders/view-all-orders");
            case 2 -> getRoute("/");
            default -> getRoute("/orders?option_not_found");
        }
    }

    public void favourites() {
        switch (readFromConsole(_appView::favourites, Integer.class)) {
            case 0 -> getRoute("/user-panel");
            default -> getRoute("/favourites?option_not_found");
        }
    }

    public void shoppingCart() {
        switch (readFromConsole(_appView::shoppingCart, Integer.class)) {
            case 0 -> getRoute("/user-panel");
            default -> getRoute("/shopping-cart?option_not_found");
        }
    }

    public void products() {
        switch (readFromConsole(_appView::products, Integer.class)) {
            case 0 -> getRoute("/user-panel");
            case 1 -> getRoute("/products/add-to-favourites");
            case 2 -> getRoute("/products/add-to-cart");
            default -> getRoute("/products?option_not_found");
        }
    }

    public void adminPanel() {

        if (!_session.getId().equals("admin@janos"))
            getRoute("/user-panel");

        switch (readFromConsole(_appView::adminPanel, Integer.class)) {
            case 0 -> getRoute("/logout");
            case 1 -> getRoute("/user-options");
            case 2 -> getRoute("/product-options");
            case 3 -> getRoute("/order-options");
            default -> getRoute("/admin-panel?option_not_found");
        }
    }

    public void userOptions() {
        switch (readFromConsole(_appView::userOptions, Integer.class)) {
            case 0 -> getRoute("/admin-panel");
            case 1 -> getRoute("/view-all-users");
            case 2 -> getRoute("/edit-user");
            case 3 -> getRoute("/remove-user");
            case 4 -> getRoute("/remove-all-users");
            default -> getRoute("/user-options?option_not_found");
        }
    }

    public void productOptions() {
        switch (readFromConsole(_appView::productOptions, Integer.class)) {
            case 0 -> getRoute("/admin-panel");
            case 1 -> getRoute("/view-all-products");
            case 2 -> getRoute("/add-product");
            case 3 -> getRoute("/edit-product");
            case 4 -> getRoute("/remove-product");
            case 5 -> getRoute("/remove-all-products");
            default -> getRoute("/product-options?option_not_found");
        }
    }

    public void orderOptions() {
        switch (readFromConsole(_appView::orderOptions, Integer.class)) {
            case 0 -> getRoute("/admin-panel");
            case 1 -> getRoute("/view-all-orders");
            case 2 -> getRoute("/edit order");
            case 3 -> getRoute("/remove-order");
            case 4 -> getRoute("/remove-all-orders");
            default -> getRoute("/order-options?option_not_found");
        }
    }


    public void logIn() {
        String email = readFromConsole(_appView::enter_email, String.class);
        String password = readFromConsole(_appView::enter_password, String.class);

        Response response = _userController.logInUser(email, password);

        switch (response) {
            case USER_LOGIN_SUCCESSFUL -> setUpSession(email);
            case INCORRECT_EMAIL -> getRoute("/?incorrect_email");
            case INCORRECT_PASSWORD -> getRoute("/?incorrect_password");
        }
    }

    public void signUp() {

        String firstname = readFromConsole(_appView::enter_firstname, String.class);
        String lastname = readFromConsole(_appView::enter_lastname, String.class);
        String email = readFromConsole(_appView::enter_email, String.class);
        String password = readFromConsole(_appView::enter_password, String.class);

        Response response = _userController.createUser(firstname, lastname, email, password);

        switch (response) {
            case USER_EXISTS -> getRoute("/?user_exists");
            case USER_CREATE_SUCCESSFUL -> getRoute("/?user_created_successfully");
            case SOMETHING_WENT_WRONG -> getRoute("/?something_went_wrong");
        }
    }

    public void logOut() {
        if (_session.destroy())
            getRoute("/?logout_successful");
    }


    public void userDetails() {
        _userController.listAllUsers();
        _appView.account_details(_userController.getUser(_session.getId()));

        while (true) {
            if (readFromConsole(_appView::print_back, Integer.class) == 0)
                userPanel();
        }
    }

    public void updateUserInfo() {
        String firstname = readFromConsole(_appView::enter_new_firstname, String.class);
        String lastname = readFromConsole(_appView::enter_new_lastname, String.class);
        String email = readFromConsole(_appView::enter_new_email, String.class);

        Response response = _userController.updateUser(firstname, lastname, email, _session.getId());
        switch (response) {
            case USER_UPDATE_SUCCESSFUL -> {
                _appView.user_updated_successfully();
                if (!email.isEmpty()) {
                    _session.destroy();
                    _session = Session.getInstance();
                    _session.setId(email);
                }
            }
            case USER_EXISTS -> _appView.user_exists();
            case SOMETHING_WENT_WRONG -> _appView.something_went_wrong();
        }
        sleep(500);
        userPanel();
    }

    public void changePassword() {
        String currentPassword = readFromConsole(_appView::enter_password, String.class);
        String newPassword = readFromConsole(_appView::enter_new_password, String.class);
        String confirmPassword = readFromConsole(_appView::confirm_password, String.class);

        Response response = _userController.updateUserPassword(currentPassword, newPassword, confirmPassword, _session.getId());

        switch (response) {
            case USER_NOT_FOUND -> _appView.user_not_found();
            case INCORRECT_PASSWORD -> _appView.incorrect_password();
            case PASSWORDS_DO_NOT_MATCH -> _appView.passwords_do_not_match();
            case PASSWORD_UPDATE_SUCCESSFUL -> _appView.password_updated_successfully();
            case SOMETHING_WENT_WRONG -> _appView.something_went_wrong();
        }
        sleep(750);
    }

    public void deleteAccount() {
        String password = readFromConsole(_appView::enter_password, String.class);

        Response response = _userController.deleteUser(_session.getId(), password);

        switch (response) {
            case INCORRECT_EMAIL -> _appView.incorrect_email();
            case INCORRECT_PASSWORD -> _appView.incorrect_password();
            case SOMETHING_WENT_WRONG -> _appView.something_went_wrong();
            case USER_DELETE_SUCCESSFUL -> {
                if (_session.destroy()) {
                    sleep(1500);
                    mainMenu();
                }
            }
        }
    }


    private void setUpSession(String email) {
        _session = Session.getInstance();
        _session.setId(email);
        _appView.login_successful();

        Env env = new Env();
        String route = Objects.equals(env.load().get("ADMIN_EMAIL"), email) ? "/admin-panel" : "/user-panel";
        getRoute(route);
    }


}
