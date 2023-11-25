package org.online_shop.controllers;

import java.awt.color.ICC_ColorSpace;
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

    private final AppView appView = new AppView();
    private final CategoryController categoryController = new CategoryController(new CategoryRepository());
    private final FavouriteController favouriteController = new FavouriteController(new FavouriteRepository());
    private final OrderController orderController = new OrderController(new OrderRepository());
    private final OrderItemController orderItemController = new OrderItemController(new OrderItemRepository());
    private final ProductController productController = new ProductController(new ProductRepository());
    private final ProductSpecController productSpecController = new ProductSpecController(new ProductSpecRepository());
    private final ShippingAddressController shippingAddressController = new ShippingAddressController(new ShippingAddressRepository());
    private final ShoppingCartController shoppingCartController = new ShoppingCartController(new ShoppingCartRepository());
    private final ShoppingCartItemController shoppingCartItemController = new ShoppingCartItemController(new ShoppingCartItemRepository());
    private final UserController userController = new UserController(new UserRepository());
    private Session session;


    private void sleep(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUpSession(String email) {
        session = Session.getInstance();
        session.setId(email);
        appView.login_successful();

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
        appView.print_goodBye();
        System.out.println(userController.listAllUsers());
        System.exit(0);
    }


    public void restartSession(String email) {
        if (!email.isEmpty()) {
            session.destroy();
            session = Session.getInstance();
            session.setId(email);
        }
        appView.user_updated_successfully();
    }

    public void deleteUser() {
        logOut();
        appView.user_deleted_successfully();
    }

    public void run() {
        mainMenu();
    }

    // Menu classes
    public void mainMenu() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(appView::mainMenu, Integer.class)) {
                case 0 -> running = false;
                case 1 -> logIn();
                case 2 -> signUp();
                default -> appView.option_not_found();
            }
        }
    }

    public void userPanel() {
        while (true) {
            switch (readFromConsole(appView::userPanel, Integer.class)) {
                case 0 -> logOut();
                case 1 -> accountSettings();
                case 2 -> shippingAddressOptions();
                case 3 -> orders();
                case 4 -> favourites();
                case 5 -> shoppingCart();
                case 6 -> products();
                default -> appView.option_not_found();
            }
        }
    }

    public void accountSettings() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(appView::accountSettings, Integer.class)) {
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
        appView.account_details(userController.getUser(session.getId()));

        while (true) {
            if (readFromConsole(appView::print_back, Integer.class) == 0)
                break;
        }
    }

    public void editProfileDetails() {
        String firstname = readFromConsole(appView::enter_new_firstname, String.class);
        String lastname = readFromConsole(appView::enter_new_lastname, String.class);
        String email = readFromConsole(appView::enter_new_email, String.class);

        Response response = userController.updateUser(firstname, lastname, email, session.getId());
        switch (response) {
            case USER_UPDATE_SUCCESSFUL -> restartSession(email);
            case USER_EXISTS -> appView.user_exists();
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
        }
    }

    public void changePassword() {
        String currentPassword = readFromConsole(appView::enter_password, String.class);
        String newPassword = readFromConsole(appView::enter_new_password, String.class);
        String confirmPassword = readFromConsole(appView::confirm_password, String.class);

        Response response = userController.updateUserPassword(currentPassword, newPassword, confirmPassword, session.getId());

        switch (response) {
            case USER_NOT_FOUND -> appView.user_not_found();
            case INCORRECT_PASSWORD -> appView.incorrect_password();
            case PASSWORDS_DO_NOT_MATCH -> appView.passwords_do_not_match();
            case PASSWORD_UPDATE_SUCCESSFUL -> appView.password_updated_successfully();
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
        }
    }

    public void deleteAccount() {
        String password = readFromConsole(appView::enter_password, String.class);

        Response response = userController.deleteUser(session.getId(), password);

        switch (response) {
            case INCORRECT_EMAIL -> appView.incorrect_email();
            case INCORRECT_PASSWORD -> appView.incorrect_password();
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
            case USER_DELETE_SUCCESSFUL -> deleteUser();
        }
    }

    public void shippingAddressOptions() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(appView::shippingAddressOptions, Integer.class)) {
                case 0 -> running = false;
                case 1 -> viewSavedAddresses();
                case 2 -> addAddress();
                case 3 -> editAddress();
                case 4 -> deleteAddress();
                case 5 -> deleteAllAddresses();
                default -> appView.option_not_found();
            }
        }
    }


    public void viewSavedAddresses() {

        User user = userController.getUser(session.getId());
        List<ShippingAddress> addresses = shippingAddressController.getAll(user);
        appView.print_addresses(addresses);

        while (true) {
            if (readFromConsole(appView::print_back, Integer.class) == 0)
                break;
        }

    }

    public void addAddress() {
        appView.addAddress();

        String name = readFromConsole(appView::enter_name, String.class);
        String phone = readFromConsole(appView::enter_phone, String.class);
        String address  = readFromConsole(appView::enter_address, String.class);
        String city = readFromConsole(appView::enter_city, String.class);
        String zipcode = readFromConsole(appView::enter_zipcode, String.class);
        User user = userController.getUser(session.getId());

        Response response = shippingAddressController.addAddress(name, phone, address, city, zipcode, user);

        switch(response) {
            case SHIPPING_ADDRESS_CREATED_SUCCESSFULLY -> appView.shipping_address_created_successfully();
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
        }
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
            switch (readFromConsole(appView::orders, Integer.class)) {
                case 0 -> running = false;
                case 1 -> viewAllOrders();
                case 2 -> viewOrder();
                default -> appView.option_not_found();
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
            if (readFromConsole(appView::favourites, Integer.class) == 0)
                running = false;
            else
                appView.option_not_found();
        }
    }

    public void shoppingCart() {
        boolean running = true;
        while (running) {
            running = readFromConsole(appView::shoppingCart, Integer.class) == 0;
        }
    }

    public void products() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(appView::products, Integer.class)) {
                case 0 -> running = false;
                case 1 -> addToFavourites();
                case 2 -> addToCart();
                default -> appView.option_not_found();
            }
        }
    }

    public void addToFavourites() {
        appView.add_to_favourites();

        Integer productId = readFromConsole(appView::enter_product_id, Integer.class);

        //   Response response = _userController.addToFavourites(productId, _session.getId());

//        switch (response) {
//            case PRODUCT_ADD_TO_FAVOURITES -> _appView.product_added_to_favourites();
//            case PRODUCT_REMOVE_FROM_FAVOURITES -> _appView.product_removed_from_favourites();
//        }
    }

    public void addToCart() {

    }

    public void adminPanel() {

        if (!session.getId().equals("admin@janos"))
            userPanel();
        while (true) {
            switch (readFromConsole(appView::adminPanel, Integer.class)) {
                case 0 -> logOut();
                case 1 -> userOptions();
                case 2 -> productOptions();
                case 3 -> orderOptions();
                default -> appView.option_not_found();
            }
        }
    }

    public void userOptions() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(appView::userOptions, Integer.class)) {
                case 0 -> running = false;
                case 1 -> viewAllUsers();
                case 2 -> editUser();
                case 3 -> removeUser();
                case 4 -> removeAllUsers();
                default -> appView.option_not_found();
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
        appView.remove_all_users();
        String adminPassword = readFromConsole(appView::enter_password, String.class);

        Response response = userController.removeALlUsers(adminPassword);

        switch (response) {
            case INCORRECT_PASSWORD -> appView.incorrect_password();
            case ALL_PRODUCTS_DELETE_SUCCESSFUL -> appView.all_products_deleted_successfully();
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
        }
    }

    public void productOptions() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(appView::productOptions, Integer.class)) {
                case 0 -> running = false;
                case 1 -> viewAllProducts();
                case 2 -> addProduct();
                case 3 -> editProduct();
                case 4 -> removeProduct();
                case 5 -> removeAllProducts();
                default -> appView.option_not_found();
            }
        }
    }

    public void viewAllProducts() {
        appView.view_all_products(productController.getALl());

        while (true) {
            if (readFromConsole(appView::print_back, Integer.class) == 0)
                userPanel();
        }
    }

    public void addProduct() {
        appView.add_product();
        String name = readFromConsole(appView::enter_product_name, String.class);
        Float price = readFromConsole(appView::enter_product_price, Float.class);

        appView.view_all_categories(categoryController.getAllCategories());
        Category category = categoryController.getCategory(readFromConsole(appView::select_category, Integer.class));

        String description = readFromConsole(appView::enter_product_description, String.class);

        Integer stock = readFromConsole(appView::enter_stock, Integer.class);

        Response response = productController.addProduct(name, price, category, description, stock);

        switch (response) {
            case PRODUCT_CREATE_SUCCESSFUL -> appView.product_added_successfully();
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
        }
    }

    public void editProduct() {
        appView.edit_product();

        Integer productId = readFromConsole(appView::enter_product_id, Integer.class);

        String name = readFromConsole(appView::enter_product_name, String.class);
        String price_str = readFromConsole(appView::enter_product_price, String.class);
        String description = readFromConsole(appView::enter_product_description, String.class);
        appView.view_all_categories(categoryController.getAllCategories());

        Category category = categoryController.getCategory(readFromConsole(appView::select_category, Integer.class));

        String stock_str = readFromConsole(appView::enter_stock, String.class);

        Response response = productController.modify(name, price_str, description, category, stock_str, productId);

        switch (response) {
            case PRODUCT_NOT_FOUND -> appView.product_not_found();
            case PRODUCT_UPDATE_SUCCESSFUL -> appView.product_updated_successfully();
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
        }
    }

    public void removeProduct() {
        appView.remove_product();
        Integer productId = readFromConsole(appView::enter_product_id, Integer.class);

        Response response = productController.removeProduct(productId);
        switch (response) {
            case PRODUCT_DELETE_SUCCESSFUL -> appView.product_deleted_successfully();
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
        }
    }

    public void removeAllProducts() {
        appView.remove_all_products();
        String adminPassword = readFromConsole(appView::enter_password, String.class);

        Response response = productController.removeAllProducts(adminPassword);

        switch (response) {
            case INCORRECT_PASSWORD -> appView.incorrect_password();
            case ALL_PRODUCTS_DELETE_SUCCESSFUL -> appView.all_products_deleted_successfully();
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
        }
    }


    public void orderOptions() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(appView::orderOptions, Integer.class)) {
                case 0 -> running = false;
                case 1 -> viewAllOrders();
                case 2 -> editOrder();
                case 3 -> removeOrder();
                case 4 -> removeAllOrders();
                default -> appView.option_not_found();
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
        String email = readFromConsole(appView::enter_email, String.class);
        String password = readFromConsole(appView::enter_password, String.class);

        Response response = userController.logInUser(email, password);

        switch (response) {
            case USER_LOGIN_SUCCESSFUL -> setUpSession(email);
            case INCORRECT_EMAIL -> appView.incorrect_email();
            case INCORRECT_PASSWORD -> appView.incorrect_password();
        }
    }

    public void signUp() {

        String firstname = readFromConsole(appView::enter_firstname, String.class);
        String lastname = readFromConsole(appView::enter_lastname, String.class);
        String email = readFromConsole(appView::enter_email, String.class);
        String password = readFromConsole(appView::enter_password, String.class);

        Response response = userController.createUser(firstname, lastname, email, password);

        switch (response) {
            case USER_EXISTS -> appView.user_exists();
            case USER_CREATE_SUCCESSFUL -> appView.user_created_successfully();
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
        }
    }

    public void logOut() {
        if (session.destroy())
            mainMenu();
    }
}
