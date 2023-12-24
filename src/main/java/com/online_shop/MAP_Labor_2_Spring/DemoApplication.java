package com.online_shop.MAP_Labor_2_Spring;

import ch.qos.logback.core.pattern.color.BoldCyanCompositeConverter;
import com.online_shop.MAP_Labor_2_Spring.controllers.ShippingAddressController;
import com.online_shop.MAP_Labor_2_Spring.controllers.UserController;
import com.online_shop.MAP_Labor_2_Spring.models.Session;
import com.online_shop.MAP_Labor_2_Spring.models.ShippingAddress;
import com.online_shop.MAP_Labor_2_Spring.models.User;
import com.online_shop.MAP_Labor_2_Spring.repositories.Env;
import com.online_shop.MAP_Labor_2_Spring.views.AppView;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.*;


@SpringBootApplication
public class DemoApplication {

    private static UserController userController;
    private static ShippingAddressController shippingAddressController;

    private static final AppView appView = new AppView();

    @Autowired
    public DemoApplication(UserController userController,
                           ShippingAddressController shippingAddressController) {
        DemoApplication.userController = userController;
        DemoApplication.shippingAddressController = shippingAddressController;
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        mainMenu();
    }

    private static void sleep(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T readFromConsole(Runnable message, Class<T> type) {
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

    private static Session session;


    private static void back() {
        while (true) {
            if (readFromConsole(appView::print_back, Integer.class) == 0)
                break;
        }
    }


    private Integer parseIntegerOrDefaultValue(String value, Integer defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    private static void setUpSession(User user) {
        session = Session.getInstance();
        session.setId(user.getId());
        appView.login_successful();
        if (Objects.equals(Env.load().get("ADMIN_EMAIL"), user.getEmail()))
            adminPanel();
        userPanel();
    }


    public void restartSession(User user) {
        if (!user.getEmail().isEmpty()) {
            session.destroy();
            session = Session.getInstance();
            session.setId(user.getId());
        }
        appView.user_updated_successfully();
    }


    public static void mainMenu() {
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

    public static void logIn() {
        String email = readFromConsole(appView::enter_email, String.class);
        String password = readFromConsole(appView::enter_password, String.class);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        ResponseEntity<User> response = userController.loginUser(user);
        HttpStatusCode statusCode = response.getStatusCode();

        if (statusCode.equals(HttpStatus.NOT_FOUND)) appView.incorrect_email();
        else if (statusCode.equals(HttpStatus.UNAUTHORIZED)) appView.incorrect_password();
        else if (statusCode.equals(HttpStatus.OK)) setUpSession(Objects.requireNonNull(response.getBody()));

    }

    public static void signUp() {

        String firstname = readFromConsole(appView::enter_firstname, String.class);
        String lastname = readFromConsole(appView::enter_lastname, String.class);
        String email = readFromConsole(appView::enter_email, String.class);
        String password = readFromConsole(appView::enter_password, String.class);

        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

        ResponseEntity<User> response = userController.createUser(user);

        HttpStatusCode statusCode = response.getStatusCode();

        if (statusCode.equals(HttpStatus.CONFLICT)) appView.user_exists();
        else appView.user_created_successfully();
    }


    public static void userPanel() {
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


    public static void accountSettings() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(appView::accountSettings, Integer.class)) {
                case 0 -> running = false;
                case 1 -> profileDetails();
                case 2 -> editProfileDetails();
//                case 3 -> changePassword();
                case 4 -> deleteAccount();
                default -> accountSettings();
            }
        }
    }


    public static void profileDetails() {
        appView.account_details(userController.getUser(session.getId()).getBody());
        back();
    }

    public static void editProfileDetails() {
        String firstname = readFromConsole(appView::enter_new_firstname, String.class);
        String lastname = readFromConsole(appView::enter_new_lastname, String.class);
        String email = readFromConsole(appView::enter_new_email, String.class);
        String password = readFromConsole(appView::enter_new_password, String.class);

        User user = userController.getUser(session.getId()).getBody();
        if (user == null) return;

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

        ResponseEntity<User> response = userController.updateUser(user);
        HttpStatusCode statusCode = response.getStatusCode();
        if (statusCode.equals(HttpStatus.NOT_FOUND)) appView.user_not_found();
        else if (statusCode.equals(HttpStatus.CONFLICT)) appView.user_exists();
        else if (statusCode.equals(HttpStatus.OK)) appView.user_updated_successfully();

    }


    public static void deleteAccount() {
        String password = readFromConsole(appView::enter_password, String.class);
        User user = userController.getUser(session.getId()).getBody();
        if (user == null) appView.user_not_found();
        else if (BCrypt.checkpw(password, user.getPassword())) appView.incorrect_password();

        ResponseEntity<String> response = userController.deleteUser(session.getId());
        deleteUser();
        appView.print_message(response.getBody());
    }

    public static void shippingAddressOptions() {
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


    public static void viewSavedAddresses() {
        User user = userController.getUser(session.getId()).getBody();
        List<ShippingAddress> addresses = shippingAddressController.getAll(user);
        appView.print_addresses(addresses);
        back();
    }

    public static void addAddress() {
        appView.addAddress();

        String name = readFromConsole(appView::enter_name, String.class);
        String phone = readFromConsole(appView::enter_phone, String.class);
        String address = readFromConsole(appView::enter_address, String.class);
        String city = readFromConsole(appView::enter_city, String.class);
        String zipcode = readFromConsole(appView::enter_zipcode, String.class);
        User user = userController.getUser(session.getId()).getBody();

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setName(name);
        shippingAddress.setPhone(phone);
        shippingAddress.setAddress(address);
        shippingAddress.setCity(city);
        shippingAddress.setZipcode(zipcode);


        ResponseEntity<ShippingAddress> response = userController.createShippingAddress(session.getId(), shippingAddress);


        switch (response) {
            case SHIPPING_ADDRESS_CREATED_SUCCESSFULLY -> appView.shipping_address_created_successfully();
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
        }
    }

    public static void editAddress() {
        Integer id = readFromConsole(appView::enter_shipping_address_number, Integer.class);

        String name = readFromConsole(appView::enter_name, String.class);
        String phone = readFromConsole(appView::enter_phone, String.class);
        String address = readFromConsole(appView::enter_address, String.class);
        String city = readFromConsole(appView::enter_city, String.class);
        String zipcode = readFromConsole(appView::enter_zipcode, String.class);
        User user = userController.getUser(session.getId(), token);

        Response response = shippingAddressController.modify(id, name, phone, address, city, zipcode, user);

        switch (response) {
            case SHOPPING_CART_DOES_NOT_EXIST -> appView.shipping_address_does_not_exist();
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
            case SHIPPING_ADDRESS_UPDATED_SUCCESSFULLY -> appView.shipping_address_updated_successfully();
        }
    }

    public static void deleteAddress() {
        Integer id = readFromConsole(appView::enter_shipping_address_number, Integer.class);
        User user = userController.getUser(session.getId(), token);

        Response response = shippingAddressController.remove(id, user);

        switch (response) {
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
            case SHIPPING_ADDRESS_DELETED_SUCCESSFULLY -> appView.shipping_address_deleted_successfully();
        }
    }

    public static void deleteAllAddresses() {
        String password = readFromConsole(appView::enter_password, String.class);
        User user = userController.getUser(session.getId(), token);

        Response response = shippingAddressController.removeAll(password, user);

        switch (response) {
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
            case INCORRECT_PASSWORD -> appView.incorrect_password();
            case ALL_SHIPPING_ADDRESSES_DELETED_SUCCESSFULLY -> appView.all_shipping_addresses_deleted_successfully();
        }
    }


    public static void orders() {
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
        User user = userController.getUser(session.getId(), token);
        List<Order> orders = orderController.getAll(user);
        appView.view_all_orders(orders);
        back();
    }

    public void viewOrder() {

        Integer id = readFromConsole(appView::enter_order_id, Integer.class);
        Order order = orderController.get(id, userController.getUser(session.getId(), token));

        appView.print_order(order);
        back();
    }


    public static void favourites() {
        back();
    }

    public static void shoppingCart() {
        back();
    }

    public static void products() {
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

        Response response = favouriteController.addOrRemoveToFavourites(session.getId(), productId);

        switch (response) {
            case PRODUCT_ADD_TO_FAVOURITES -> appView.product_added_to_favourites();
            case PRODUCT_REMOVE_FROM_FAVOURITES -> appView.product_removed_from_favourites();
        }
    }


    public void addToCart() {
        List<String> productIdAndQuantity = List.of(readFromConsole(appView::add_to_cart, String.class).split("&"));

        Integer productId = parseIntegerOrDefaultValue(productIdAndQuantity.get(0), -1);
        Integer quantity = productIdAndQuantity.size() == 1 ? -1 : parseIntegerOrDefaultValue(productIdAndQuantity.get(1), -1);

        User user = userController.getUser(session.getId(), token);
        ShoppingCart shoppingCart = shoppingCartController.get(user);

        if (shoppingCart.getUser().getEmail() == null) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
        }

        Response response = shoppingCartItemController.addToCart(shoppingCart.getId(), productId, quantity);

        switch (response) {
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
            case PRODUCT_ADDED_TO_CART_SUCCESSFULLY -> appView.product_Added_to_cart_successully();
        }
    }

    public static void adminPanel() {

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
        appView.view_all_users();
        for (User user : (List<User>) userController.getAll(token))
            appView.list_all_users(user);
        back();
    }

    public void editUser() {
        appView.edit_user();

        String userEmail = readFromConsole(appView::enter_user_email, String.class);
        String firstname = readFromConsole(appView::enter_firstname, String.class);
        String lastname = readFromConsole(appView::enter_lastname, String.class);
        String email = readFromConsole(appView::enter_email, String.class);

        User user = userController.getUser(userEmail, token);

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);

        Response response = userController.updateUser(token, userEmail, user);

        switch (response) {
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
            case USER_NOT_FOUND -> appView.user_not_found();
            case USER_EXISTS -> appView.user_exists();
            case USER_UPDATE_SUCCESSFUL -> appView.user_updated_successfully();
        }
    }


    public void removeUser() {
        appView.remove_user();

        String userEmail = readFromConsole(appView::enter_user_email, String.class);
        Response response = userController.deleteUser(userEmail, token, Env.load().get("ADMIN_PASSWORD"));
        switch (response) {
            case SOMETHING_WENT_WRONG -> appView.something_went_wrong();
            case INCORRECT_EMAIL -> appView.incorrect_email();
            case USER_DELETE_SUCCESSFUL -> appView.user_deleted_successfully();
        }
    }

    public void removeAllUsers() {
        appView.remove_all_users();
        String adminPassword = readFromConsole(appView::enter_password, String.class);

        Response response = userController.deleteAllUsers(adminPassword);

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
        for (Product product : productController.getALl())
            appView.view_all_products(product);
        back();
    }

    public void addProduct() {
        appView.add_product();
        String name = readFromConsole(appView::enter_product_name, String.class);
        Float price = readFromConsole(appView::enter_product_price, Float.class);

        appView.view_all_categories(categoryController.getAllCategories());
        Category category = categoryController.getCategory(readFromConsole(appView::select_category, Integer.class));

        String description = readFromConsole(appView::enter_product_description, String.class);

        Integer stock = readFromConsole(appView::enter_stock, Integer.class);

        Response response = productController.createProduct(name, price, category, description, stock);

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
                case 1 -> viewAllUsersOrders();
                case 2 -> editOrder();
                case 3 -> removeOrder();
                case 4 -> removeAllOrders();
                default -> appView.option_not_found();
            }
        }
    }

    public void viewAllUsersOrders() {

        List<Order> orders = orderController.getAll();

//        appView.view_all_users_orders(orders);

        back();
    }

    public void editOrder() {

    }

    public void removeOrder() {

    }

    public void removeAllOrders() {

    }


    public static void logOut() {
        if (session.destroy())
            mainMenu();
    }

    public static void deleteUser() {
        logOut();
        appView.user_deleted_successfully();
    }

    public void run() {
        mainMenu();
    }

}
