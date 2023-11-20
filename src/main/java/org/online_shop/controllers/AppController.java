package org.online_shop.controllers;

import java.lang.Thread;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.online_shop.enums.Response;
import org.online_shop.models.*;
import org.online_shop.repositories.*;
import org.online_shop.views.AppView;


public class AppController {

    private final AppView _appView = new AppView();
    private final CategoryController _categoryController = new CategoryController(new CategoryRepository());
    private final FavouriteController _favouriteController = new FavouriteController(new FavouriteRepository());
    private final OrderController _orderController = new OrderController(new OrderRepository());
    private final OrderItemController _orderItemController = new OrderItemController(new OrderItemRepository());
    private final ProductController _productController = new ProductController(new ProductRepository());
    private final ProductSpecController _productSpecController = new ProductSpecController(new ProductSpecRepository());
    private final ShippingAddressController _shippingAddressController = new ShippingAddressController(new ShippingAddressRepository());
    private final ShoppingCartController _shoppingCartController = new ShoppingCartController(new ShoppingCartRepository());
    private final ShoppingCartItemController _shoppingCartItemController = new ShoppingCartItemController(new ShoppingCartItemRepository());
    private final UserController _userController = new UserController(new UserRepository());
    private Session _session;


    private void sleep(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUpSession(String email) {
        _session = Session.getInstance();
        _session.setId(email);
        _appView.login_successful();

        Env env = new Env();
        if (Objects.equals(env.load().get("ADMIN_EMAIL"), email))
            adminPanel();
        userPanel();
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


    public void quit() {
        _appView.print_goodBye();
        _userController.listAllUsers();
        System.exit(0);
    }

    public void updateSession() {
        if (_session.destroy()) {
            sleep(1500);
            accountSettings();
        }
    }

    public void restart_session(String email) {
        if (!email.isEmpty()) {
            _session.destroy();
            _session = Session.getInstance();
            _session.setId(email);
        }
        _appView.user_updated_successfully();
    }

    public void deleteUser() {
        logOut();
        _appView.user_deleted_successfully();
    }

    public void run() {
        mainMenu();
    }

    // Menu classes
    public void mainMenu() {
//        _userController.createAdmin();
        boolean running = true;
        while (running) {
            switch (readFromConsole(_appView::mainMenu, Integer.class)) {
                case 0 -> running = false;
                case 1 -> logIn();
                case 2 -> signUp();
                default -> _appView.option_not_found();
            }
        }
    }

    public void userPanel() {
        while (true) {
            switch (readFromConsole(_appView::userPanel, Integer.class)) {
                case 0 -> logOut();
                case 1 -> accountSettings();
                case 2 -> shippingAddressOptions();
                case 3 -> orders();
                case 4 -> favourites();
                case 5 -> shoppingCart();
                case 6 -> products();
                default -> _appView.option_not_found();
            }
        }
    }

    public void accountSettings() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(_appView::accountSettings, Integer.class)) {
                case 0 -> running = false;
                case 1 -> profileDetails();
                case 2 -> editProfileDetails();
                case 3 -> changePassword();
                case 4 -> deleteAccount();
                default -> accountSettings();
            }
        }
    }


    public void profileDetails() {
        _userController.listAllUsers();
        _appView.account_details(_userController.getUser(_session.getId()));

        while (true) {
            if (readFromConsole(_appView::print_back, Integer.class) == 0)
                userPanel();
        }
    }

    public void editProfileDetails() {
        String firstname = readFromConsole(_appView::enter_new_firstname, String.class);
        String lastname = readFromConsole(_appView::enter_new_lastname, String.class);
        String email = readFromConsole(_appView::enter_new_email, String.class);

        Response response = _userController.updateUser(firstname, lastname, email, _session.getId());
        switch (response) {
            case USER_UPDATE_SUCCESSFUL -> restart_session(email);
            case USER_EXISTS -> _appView.user_exists();
            case SOMETHING_WENT_WRONG -> _appView.something_went_wrong();
        }
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
    }

    public void deleteAccount() {
        String password = readFromConsole(_appView::enter_password, String.class);

        Response response = _userController.deleteUser(_session.getId(), password);

        switch (response) {
            case INCORRECT_EMAIL -> _appView.incorrect_email();
            case INCORRECT_PASSWORD -> _appView.incorrect_password();
            case SOMETHING_WENT_WRONG -> _appView.something_went_wrong();
            case USER_DELETE_SUCCESSFUL -> deleteUser();
        }
    }

    public void shippingAddressOptions() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(_appView::shippingAddressOptions, Integer.class)) {
                case 0 -> running = false;
                case 1 -> viewSavedAddresses();
                case 2 -> addAddress();
                case 3 -> editAddress();
                case 4 -> deleteAddress();
                case 5 -> deleteAllAddresses();
                default -> _appView.option_not_found();
            }
        }
    }


    public void viewSavedAddresses() {

        User user = _userController.getUser(_session.getId());
        List<ShippingAddress> addresses = _shippingAddressController.getAll(user);
        //user.set_shoppingCart(addresses);
    }

    public void addAddress() {

    }

    public void editAddress() {

    }

    public void deleteAddress() {

    }

    public void deleteAllAddresses() {

    }


    public void orders() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(_appView::orders, Integer.class)) {
                case 0 -> running = false;
                case 1 -> viewAllOrders();
                case 2 -> viewOrder();
                default -> _appView.option_not_found();
            }
        }
    }

    public void viewAllOrders() {

    }

    public void viewOrder() {

    }


    public void favourites() {
        boolean running = true;
        while (running) {
            if (readFromConsole(_appView::favourites, Integer.class) == 0)
                running = false;
            else
                _appView.option_not_found();
        }
    }

    public void shoppingCart() {
        boolean running = true;
        while (running) {
            running = readFromConsole(_appView::shoppingCart, Integer.class) == 0;
        }
    }

    public void products() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(_appView::products, Integer.class)) {
                case 0 -> running = false;
                case 1 -> addToFavourites();
                case 2 -> addToCart();
                default -> _appView.option_not_found();
            }
        }
    }

    public void addToFavourites() {
        _appView.add_to_favourites();

        Integer productId = readFromConsole(_appView::enter_product_id, Integer.class);

        //   Response response = _userController.addToFavourites(productId, _session.getId());

//        switch (response) {
//            case PRODUCT_ADD_TO_FAVOURITES -> _appView.product_added_to_favourites();
//            case PRODUCT_REMOVE_FROM_FAVOURITES -> _appView.product_removed_from_favourites();
//        }
    }

    public void addToCart() {

    }

    public void adminPanel() {

        if (!_session.getId().equals("admin@janos"))
            userPanel();
        while (true) {
            switch (readFromConsole(_appView::adminPanel, Integer.class)) {
                case 0 -> logOut();
                case 1 -> userOptions();
                case 2 -> productOptions();
                case 3 -> orderOptions();
                default -> _appView.option_not_found();
            }
        }
    }

    public void userOptions() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(_appView::userOptions, Integer.class)) {
                case 0 -> running = false;
                case 1 -> viewAllUsers();
                case 2 -> editUser();
                case 3 -> removeUser();
                case 4 -> removeAllUsers();
                default -> _appView.option_not_found();
            }
        }
    }


    public void viewAllUsers() {

    }

    public void editUser() {

    }

    public void removeUser() {

    }

    public void removeAllUsers() {
        _appView.remove_all_users();
        String adminPassword = readFromConsole(_appView::enter_password, String.class);

        Response response = _userController.removeALlUsers(adminPassword);

        switch (response) {
            case INCORRECT_PASSWORD -> _appView.incorrect_password();
            case ALL_PRODUCTS_DELETE_SUCCESSFUL -> _appView.all_products_deleted_successfully();
            case SOMETHING_WENT_WRONG -> _appView.something_went_wrong();
        }
    }

    public void productOptions() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(_appView::productOptions, Integer.class)) {
                case 0 -> running = false;
                case 1 -> viewAllProducts();
                case 2 -> addProduct();
                case 3 -> editProduct();
                case 4 -> removeProduct();
                case 5 -> removeAllProducts();
                default -> _appView.option_not_found();
            }
        }
    }

    public void viewAllProducts() {
        _appView.view_all_products(_productController.getALl());

        while (true) {
            if (readFromConsole(_appView::print_back, Integer.class) == 0)
                userPanel();
        }
    }

    public void addProduct() {
        _appView.add_product();
        String name = readFromConsole(_appView::enter_product_name, String.class);
        Float price = readFromConsole(_appView::enter_product_price, Float.class);

        _appView.view_all_categories(_categoryController.getAllCategories());
        Category category = _categoryController.getCategory(readFromConsole(_appView::select_category, Integer.class));

        String description = readFromConsole(_appView::enter_product_description, String.class);

        Integer stock = readFromConsole(_appView::enter_stock, Integer.class);

        Response response = _productController.addProduct(name, price, category, description, stock);

        switch (response) {
            case PRODUCT_CREATE_SUCCESSFUL -> _appView.product_added_successfully();
            case SOMETHING_WENT_WRONG -> _appView.something_went_wrong();
        }
    }

    public void editProduct() {
        _appView.edit_product();

        Integer productId = readFromConsole(_appView::enter_product_id, Integer.class);

        String name = readFromConsole(_appView::enter_product_name, String.class);
        String price_str = readFromConsole(_appView::enter_product_price, String.class);
        String description = readFromConsole(_appView::enter_product_description, String.class);
        _appView.view_all_categories(_categoryController.getAllCategories());

        Category category = _categoryController.getCategory(readFromConsole(_appView::select_category, Integer.class));

        String stock_str = readFromConsole(_appView::enter_stock, String.class);

        Response response = _productController.modify(name, price_str, description, category, stock_str, productId);

        switch (response) {
            case PRODUCT_NOT_FOUND -> _appView.product_not_found();
            case PRODUCT_UPDATE_SUCCESSFUL -> _appView.product_updated_successfully();
            case SOMETHING_WENT_WRONG -> _appView.something_went_wrong();
        }
    }

    public void removeProduct() {
        _appView.remove_product();
        Integer productId = readFromConsole(_appView::enter_product_id, Integer.class);

        Response response = _productController.removeProduct(productId);
        switch (response) {
            case PRODUCT_DELETE_SUCCESSFUL -> _appView.product_deleted_successfully();
            case SOMETHING_WENT_WRONG -> _appView.something_went_wrong();
        }
    }

    public void removeAllProducts() {
        _appView.remove_all_products();
        String adminPassword = readFromConsole(_appView::enter_password, String.class);

        Response response = _productController.removeAllProducts(adminPassword);

        switch (response) {
            case INCORRECT_PASSWORD -> _appView.incorrect_password();
            case ALL_PRODUCTS_DELETE_SUCCESSFUL -> _appView.all_products_deleted_successfully();
            case SOMETHING_WENT_WRONG -> _appView.something_went_wrong();
        }
    }


    public void orderOptions() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(_appView::orderOptions, Integer.class)) {
                case 0 -> running = false;
                case 1 -> viewAllOrders();
                case 2 -> editOrder();
                case 3 -> removeOrder();
                case 4 -> removeAllOrders();
                default -> _appView.option_not_found();
            }
        }
    }

    public void viewAllUsersOrders() {

    }

    public void editOrder() {

    }

    public void removeOrder() {

    }

    public void removeAllOrders() {

    }


    public void logIn() {
        String email = readFromConsole(_appView::enter_email, String.class);
        String password = readFromConsole(_appView::enter_password, String.class);

        Response response = _userController.logInUser(email, password);

        switch (response) {
            case USER_LOGIN_SUCCESSFUL -> setUpSession(email);
            case INCORRECT_EMAIL -> _appView.incorrect_email();
            case INCORRECT_PASSWORD -> _appView.incorrect_password();
        }
    }

    public void signUp() {

        String firstname = readFromConsole(_appView::enter_firstname, String.class);
        String lastname = readFromConsole(_appView::enter_lastname, String.class);
        String email = readFromConsole(_appView::enter_email, String.class);
        String password = readFromConsole(_appView::enter_password, String.class);

        Response response = _userController.createUser(firstname, lastname, email, password);

        switch (response) {
            case USER_EXISTS -> _appView.user_exists();
            case USER_CREATE_SUCCESSFUL -> _appView.user_created_successfully();
            case SOMETHING_WENT_WRONG -> _appView.something_went_wrong();
        }
    }

    public void logOut() {
        if (_session.destroy())
            mainMenu();
    }
}
