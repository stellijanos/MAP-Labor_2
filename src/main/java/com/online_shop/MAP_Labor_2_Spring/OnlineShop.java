package com.online_shop.MAP_Labor_2_Spring;

import com.online_shop.MAP_Labor_2_Spring.controllers.*;
import com.online_shop.MAP_Labor_2_Spring.models.*;
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
public class OnlineShop {

    private static UserController userController;
    private static ShippingAddressController shippingAddressController;
    private static OrderController orderController;
    private static FavouriteController favouriteController;
    private static ShoppingCartController shoppingCartController;
    private static ProductController productController;
    private static CategoryController categoryController;

    private static final AppView appView = new AppView();

    @Autowired
    public OnlineShop(UserController userController,
                      ShippingAddressController shippingAddressController,
                      OrderController orderController,
                      FavouriteController favouriteController,
                      ShoppingCartController shoppingCartController,
                      ProductController productController,
                      CategoryController categoryController) {
        OnlineShop.userController = userController;
        OnlineShop.shippingAddressController = shippingAddressController;
        OnlineShop.orderController = orderController;
        OnlineShop.favouriteController = favouriteController;
        OnlineShop.shoppingCartController = shoppingCartController;
        OnlineShop.productController = productController;
        OnlineShop.categoryController = categoryController;
    }


    public static void main(String[] args) {
        SpringApplication.run(OnlineShop.class, args);
        OnlineShop.run();
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
                if (type.equals(Long.class))
                    return type.cast(scanner.nextInt());

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
        ResponseEntity<Iterable<ShippingAddress>> addresses = userController.getAllShippingAddresses(session.getId());

        if (addresses.getStatusCode().equals(HttpStatus.NOT_FOUND))
            return;

        for (ShippingAddress shippingAddress : Objects.requireNonNull(addresses.getBody()))
            appView.print_address(shippingAddress);
        back();
    }

    public static void addAddress() {
        appView.addAddress();

        String name = readFromConsole(appView::enter_name, String.class);
        String phone = readFromConsole(appView::enter_phone, String.class);
        String address = readFromConsole(appView::enter_address, String.class);
        String city = readFromConsole(appView::enter_city, String.class);
        String zipcode = readFromConsole(appView::enter_zipcode, String.class);

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setName(name);
        shippingAddress.setPhone(phone);
        shippingAddress.setAddress(address);
        shippingAddress.setCity(city);
        shippingAddress.setZipcode(zipcode);

        ResponseEntity<ShippingAddress> response = userController.createShippingAddress(session.getId(), shippingAddress);
        HttpStatusCode statusCode = response.getStatusCode();

        if (statusCode.equals(HttpStatus.NOT_FOUND)) appView.user_not_found();
        else if (statusCode.equals(HttpStatus.OK)) appView.shipping_address_created_successfully();

    }

    public static void editAddress() {
        Long id = readFromConsole(appView::enter_shipping_address_number, Long.class);

        String name = readFromConsole(appView::enter_name, String.class);
        String phone = readFromConsole(appView::enter_phone, String.class);
        String address = readFromConsole(appView::enter_address, String.class);
        String city = readFromConsole(appView::enter_city, String.class);
        String zipcode = readFromConsole(appView::enter_zipcode, String.class);

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setId(id);
        shippingAddress.setName(name);
        shippingAddress.setPhone(phone);
        shippingAddress.setAddress(address);
        shippingAddress.setCity(city);
        shippingAddress.setZipcode(zipcode);

        ResponseEntity<ShippingAddress> response = userController.updateShippingAddress(session.getId(), shippingAddress);

        HttpStatusCode statusCode = response.getStatusCode();

        if (statusCode.equals(HttpStatus.NOT_FOUND)) appView.not_found();
        else if (statusCode.equals(HttpStatus.BAD_REQUEST)) appView.bad_request();
        else if (statusCode.equals(HttpStatus.OK)) appView.shipping_address_updated_successfully();
    }

    public static void deleteAddress() {
        Long id = readFromConsole(appView::enter_shipping_address_number, Long.class);

        ResponseEntity<String> response = userController.deleteShippingAddress(session.getId(), id);

        HttpStatusCode statusCode = response.getStatusCode();

        if (statusCode.equals(HttpStatus.NOT_FOUND)) appView.not_found();
        else if (statusCode.equals(HttpStatus.BAD_REQUEST)) appView.bad_request();
        else if (statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR)) appView.internal_server_error();
        else if (statusCode.equals(HttpStatus.OK)) appView.shipping_address_deleted_successfully();
    }

    public static void deleteAllAddresses() {

        String password = readFromConsole(appView::enter_password, String.class);
        ResponseEntity<User> response = userController.getUser(session.getId());

        if (response.getStatusCode().equals(HttpStatus.NOT_FOUND))
            appView.not_found();
        else if (response.getStatusCode().equals(HttpStatus.OK)) {
            User user = response.getBody();

            if (user != null && !BCrypt.checkpw(password, user.getPassword())) {
                appView.incorrect_password();
            }
            ResponseEntity<String> result = userController.deleteAllShippingAddress(session.getId());

            if (result.getStatusCode().equals(HttpStatus.NOT_FOUND)) appView.not_found();
            else appView.print_message(result.getBody());
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

    public static void viewAllOrders() {
        ResponseEntity<Iterable<Order>> orders = orderController.getAllOrders(session.getId());
        if (orders.getStatusCode().equals(HttpStatus.NOT_FOUND)) appView.not_found();
        else {
            for (Order order : Objects.requireNonNull(orders.getBody())) {
                System.out.println(order);
            }
        }
        back();
    }

    public static void viewOrder() {
        Long id = readFromConsole(appView::enter_order_id, Long.class);
        Order order = orderController.getOrder(session.getId(), id).getBody();
        appView.print_order(order);
        back();
    }


    public static void favourites() {
        Iterable<Product> products = favouriteController.getAllFavouriteProducts(session.getId()).getBody();
        if (products != null) {
            for (Product product : products)
                System.out.println(product);
        }
        back();
    }

    public static void shoppingCart() {
        ShoppingCart shoppingCart = shoppingCartController.getShoppingCart(session.getId()).getBody();
        if (shoppingCart != null) {
            for (ShoppingCartItem item : shoppingCart.getShoppingCartItems())
                System.out.println(item);
        }

        boolean running = true;
        while (running) {
            switch (readFromConsole(appView::shopping_cart_options, Integer.class)) {
                case 0 -> running = false;
                case 1 -> createOrder();
                default -> appView.option_not_found();
            }
        }
    }

    public static void createOrder() {

        ResponseEntity<ShoppingCart> cartOptional = shoppingCartController.getShoppingCart(session.getId());

        if (cartOptional.getStatusCode().equals(HttpStatus.NOT_FOUND)) return;

        List<ShoppingCartItem> items = (List<ShoppingCartItem>) Objects.requireNonNull(cartOptional.getBody()).getShoppingCartItems();

        Set<OrderItem> orderItems = new HashSet<>();

        for (ShoppingCartItem item : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getProduct().getPrice());

            orderItems.add(orderItem);
        }

        List<ShippingAddress> addresses = (List<ShippingAddress>) shippingAddressController.getAllShippingAddresses().getBody();

        ShippingAddress shippingAddress = null;

        while (shippingAddress == null) {


            Long id = readFromConsole(appView::enter_shipping_address_number, Long.class);

            shippingAddress = shippingAddressController.getShippingAddress(id).getBody();
        }

        try {
            Order order = new Order()
                    .orderItems(orderItems)
                    .user(userController.getUser(session.getId()).getBody())
                    .shippingAddress(shippingAddress)
                    .shippingFee(15F)
                    .payment("card")
                    .status("Registered");

            ResponseEntity<Order> response = orderController.createOrder(session.getId(), order);

            System.out.println(response);

        } catch (Exception e) {
            return;
        }


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

    public static void addToFavourites() {
        appView.add_to_favourites();

        Long product_id = readFromConsole(appView::enter_product_id, Long.class);

        List<Product> favourites = (List<Product>) favouriteController.getAllFavouriteProducts(session.getId()).getBody();
        if (favourites != null) {
            Product product = favourites.stream().filter(fav -> fav.getId().equals(product_id)).findFirst().orElse(null);
            ResponseEntity<Product> response = favouriteController.addOrRemoveFavourite(session.getId(), product_id);
            if (product == null) appView.product_added_to_favourites();
            else appView.product_removed_from_favourites();
        }
    }


    public static void addToCart() {
        appView.add_to_cart();

        Long product_id = readFromConsole(appView::enter_product_id, Long.class);

        ResponseEntity<ShoppingCartItem> response = shoppingCartController.addToShoppingCart(session.getId(), product_id, 1);

        HttpStatusCode statusCode = response.getStatusCode();

        if (statusCode.equals(HttpStatus.NOT_FOUND)) appView.not_found();
        else System.out.println(response.getBody());
    }

    public static void adminPanel() {
        while (true) {
            switch (readFromConsole(appView::adminPanel, Integer.class)) {
                case 0 -> logOut();
                case 1 -> userOptions();
                case 2 -> productOptions();
                default -> appView.option_not_found();
            }
        }
    }

    public static void userOptions() {
        boolean running = true;
        while (running) {
            switch (readFromConsole(appView::userOptions, Integer.class)) {
                case 0 -> running = false;
                case 1 -> viewAllUsers();
                default -> appView.option_not_found();
            }
        }
    }


    public static void viewAllUsers() {
        appView.view_all_users();
        List<User> users = (List<User>) userController.getAllUsers().getBody();
        if (users != null) {
            for (User user : users)
                appView.list_all_users(user);
        }
        back();
    }


    public static void productOptions() {
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

    public static void viewAllProducts() {
        List<Product> products = (List<Product>) productController.getAllProducts().getBody();
        if (products != null) {
            for (Product product : products)
                System.out.println(product);
        }
        back();
    }

    public static void addProduct() {
        appView.add_product();
        String name = readFromConsole(appView::enter_product_name, String.class);
        Float price = readFromConsole(appView::enter_product_price, Float.class);
        String description = readFromConsole(appView::enter_product_description, String.class);
        Integer stock = readFromConsole(appView::enter_stock, Integer.class);

        List<Category> categories = (List<Category>) categoryController.getAllCategories().getBody();

        appView.view_all_categories(categories);
        Category category = null;

        while (category == null) {
            category = categoryController.getCategory(readFromConsole(appView::select_category, Long.class)).getBody();
        }

        Product product = new Product();

        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setDescription(description);
        product.setStock(stock);

        ResponseEntity<Product> response = productController.createProduct(product);

        if (response.getStatusCode().equals(HttpStatus.CONFLICT)) appView.product_already_exists();
        else appView.product_added_successfully();
    }

    public static void editProduct() {
        appView.edit_product();

        Long productId = readFromConsole(appView::enter_product_id, Long.class);

        String name = readFromConsole(appView::enter_product_name, String.class);
        Float price = readFromConsole(appView::enter_product_price, Float.class);
        String description = readFromConsole(appView::enter_product_description, String.class);
        List<Category> categories = (List<Category>) categoryController.getAllCategories().getBody();

        appView.view_all_categories(categories);
        Category category = null;

        while (category == null) {
            category = categoryController.getCategory(readFromConsole(appView::select_category, Long.class)).getBody();
        }

        Product product = new Product();
        product.setId(productId);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(category);

        ResponseEntity<Product> response = productController.updateProduct(product);
        System.out.println(response.getBody());
    }

    public static void removeProduct() {
        appView.remove_product();
        Long productId = readFromConsole(appView::enter_product_id, Long.class);
        ResponseEntity<String> response = productController.deleteProduct(productId);
        System.out.println(response);
    }

    public static void removeAllProducts() {
        appView.remove_all_products();
        String adminPassword = readFromConsole(appView::enter_password, String.class);

        if (!BCrypt.checkpw(adminPassword, Env.load().get("ADMIN_PASSWORD"))) {
            System.out.println("Incorrect password");
            return;
        }
        ResponseEntity<String> response = productController.deleteAllProducts();

        System.out.println(response);
    }

    public static void logOut() {
        if (session.destroy())
            mainMenu();
    }

    public static void deleteUser() {
        logOut();
        appView.user_deleted_successfully();
    }

    public static void run() {
        mainMenu();
    }

}
