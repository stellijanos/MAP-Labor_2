package com.online_shop.MAP_Labor_2_Spring.views;

import com.online_shop.MAP_Labor_2_Spring.models.*;

import java.util.List;

public class AppView {
    public String enterOption() {
        return "\n\nEnter option please:\n>>>";
    }

    public void mainMenu() {
        System.out.println("""
                ------------------------------------------------
                @author Janos Stelli
                ------------------------------------------------
                Welcome to the online shop
                ------------------------------------------------
                Options:
                0. Quit App
                1. LogIn
                2. SignUp
                """ + enterOption()
        );
    }

    public void print_message(String message) {
        System.out.println(message);
    }

    public void option_not_found() {
        System.out.println("!!!!!!!!!!!!!!! Option not found, please try again! !!!!!!!!!!!!!!!\n");
    }

    public void print_goodBye() {
        System.out.println("Goodbye, see you next time!\n---------------------\n");
    }

    public void userPanel() {
        System.out.println("""
                ------------------------------
                User Panel
                ------------------------------
                0. LogOut
                1. Account settings
                2. Shipping Address options
                3. Orders
                4. Favourites
                5. ShoppingCart
                6. Products
                """ + enterOption()
        );
    }

    // userPanel option 1
    public void accountSettings() {
        System.out.println("""
                ------------------------------
                Account settings
                ------------------------------
                0. Back
                1. View profile details
                2. Edit profile details
                3. Change password
                4. Delete account
                """ + enterOption()
        );
    }

    // userPanel option 2
    public void shippingAddressOptions() {
        System.out.println("""
                ------------------------------
                Shipping Address options
                ------------------------------
                0. Back
                1. View saved addresses
                2. Add address
                3. Edit address
                4. Delete address
                5. Delete all addresses
                """ + enterOption()
        );
    }

    // userPanel option 3
    public void orders() {
        System.out.println("\n------------------------------\n" +
                "Shipping Address options\n------------------------------\n" +
                "0. Back\n" +
                "1. View all orders\n" +
                "2. View order\n" +
                enterOption()
        );
    }

    // userPanel option 4

    public void favourites() {
        System.out.println("\n---------------------\n" +
                "Shopping cart" +
                "\n---------------------\n" +
                "Options:" +
                "0. Back" +
                "\n---------------------\n" +
                enterOption()
        );
    }

    // userPanel option 5

    public void shoppingCart() {
        System.out.println("""
                ---------------------
                Shopping cart
                ---------------------
                Options:
                0. Back
                --------------------
                """ +
                enterOption()
        );
    }

    // userPanel option 6

    public void products() {
        System.out.println("""
                ---------------------
                Products
                ---------------------
                Options:0. Back1. Add To Favourites2. Add To Cart
                ---------------------
                """);
    }

    public void print_adminOption() {
        System.out.println("9. Admin Panel");
    }

    public void adminPanel() {
        System.out.println("""
                ------------------------------
                Admin Panel
                ------------------------------
                0. Back
                1. User options
                2. Product option
                3. Order options
                """ +
                enterOption()
        );
    }

    // adminPanel option 1
    public void userOptions() {
        System.out.println("\n------------------------------\n" +
                "User options\n------------------------------\n" +
                "0. Back\n" +
                "1. View all users\n" +
                "2. Edit user\n" +
                "3. Remove user\n" +
                "4. Remove all users" +
                enterOption()
        );
    }

    // adminPanel option 2
    public void productOptions() {
        System.out.println("\n------------------------------\n" +
                "Product options\n------------------------------\n" +
                "0. Back\n" +
                "1. View all products\n" +
                "2. Add product\n" +
                "3. Edit product\n" +
                "4. Remove product\n" +
                "5. Remove all products" +
                enterOption()
        );
    }

    public void orderOptions() {
        System.out.println("\n------------------------------\n" +
                "User options\n------------------------------\n" +
                "0. Back\n" +
                "1. View all users orders\n" +
                "2. Edit order\n" +
                "3. Remove order\n" +
                "4. Remove all orders" +
                enterOption()
        );
    }


    public void print_back() {
        System.out.println("0. Back");
    }

    public void no_response() {
    }


    public void view(User user) {
        System.out.println(user);
    }

    public void print_viewAll(List<User> users) {
        System.out.println("Here are all the registered users:\n----------------------------\n");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void enter_firstname() {
        System.out.println("Enter firstname: ");
    }

    public void enter_lastname() {
        System.out.println("Enter lastname: ");
    }

    public void enter_email() {
        System.out.println("Enter email address: ");
    }

    public void enter_password() {
        System.out.println("Enter password: ");
    }

    public void user_exists() {
        System.out.println("User already exists");
    }

    public void incorrect_password() {
        System.out.println("Incorrect password");
    }

    public void incorrect_email() {
        System.out.println("Incorrect email address!");
    }

    public void user_created_successfully() {
        System.out.println("User created successfully!");
    }

    public void something_went_wrong() {
        System.out.println("Something went wrong!");
    }

    public void login_successful() {
        System.out.println("Login successful!");
    }

    public void login_failed() {
        System.out.println("Login failed");
    }

    public void account_details(User user) {
        System.out.printf("""
                        -------------------
                        Account details
                        -------------------
                        Firstname: %s
                        Lastname:  %s
                        Email:     %s
                       """, user.getFirstname(), user.getLastname(), user.getEmail());
    }

    public void enter_new_password() {
        System.out.println("Enter new Password: ");
    }

    public void confirm_password() {
        System.out.println("Confirm password: ");
    }

    public void passwords_do_not_match() {
        System.out.println("Passwords don't match!");
    }

    public void enter_new_firstname() {
        System.out.println("Enter new firstname: ");
    }

    public void enter_new_lastname() {
        System.out.println("Enter new lastname");
    }

    public void enter_new_email() {
        System.out.println("Enter new email address: ");
    }

    public void user_updated_successfully() {
        System.out.println("User updated successfully!");
    }

    public void user_deleted_successfully() {
        System.out.println("User deleted successfully");
    }

    public void user_not_found() {
        System.out.println("User not found!");
    }

    public void password_updated_successfully() {
        System.out.println("Password updated successfully");
    }

    public void logout_successful() {
        System.out.println("\n----------------\nLogout successful!\n----------------\n");
    }


    public void view_all_products(List<Product> products) {
        System.out.println(products);
    }

    public void add_product() {
        System.out.println("\n-------------------\nAdd new product\n-------------------\n");
    }

    public void enter_product_name() {
        System.out.println("Enter product name: ");
    }


    public void enter_product_price() {
        System.out.println("Enter product price: ");
    }

    public void select_category() {
        System.out.println("Select a category: ");
    }


    public void view_all_categories(List<Category> categories) {
        System.out.println("Here are all the categories:\n" + categories);
    }


    public void enter_product_description() {
        System.out.println("Enter product description: ");
    }

    public void enter_stock() {
        System.out.println("Enter stock");
    }

    public void product_added_successfully() {
        System.out.println("Product added successfully!");
    }

    public void enter_product_id() {
        System.out.println("Enter product id: ");
    }

    public void remove_product() {
        System.out.println("\n-------------------\nRemove product\n-------------------\n");
    }

    public void remove_all_products() {
        System.out.println("\n-------------------\nRemove all product\n-------------------\n");
    }

    public void product_deleted_successfully() {
        System.out.println("Product deleted successfully");
    }

    public void edit_product() {
        System.out.println("\n-------------------\nEdit product\n-------------------\n");
    }

    public void product_not_found() {
        System.out.println("Product not found!");
    }

    public void product_updated_successfully() {
        System.out.println("Product updated successfully!");
    }

    public void all_products_deleted_successfully() {
        System.out.println("All products deleted successfully!");
    }

    public void remove_all_users() {
        System.out.println("Remove all users");
    }

    public void add_to_favourites() {
        System.out.println("\n-------------------\nAdd to favourites\n-------------------\n");
    }


    public void product_added_to_favourites() {
        System.out.println("Product added to favourites");
    }

    public void product_removed_from_favourites() {
        System.out.println("Product removed from favourites");
    }

    public void print_address(ShippingAddress address) {
        System.out.println(address);
    }

    public void addAddress() {
        System.out.println("------------------\nAdd Address\n------------------\n");
    }

    public void enter_name() {
        System.out.println("Enter name: ");
    }

    public void enter_phone() {
        System.out.println("Enter phone: ");
    }

    public void enter_address() {
        System.out.println("Enter street and number: ");
    }

    public void enter_city() {
        System.out.println("Enter city: ");
    }

    public void enter_zipcode() {
        System.out.println("Enter zipcode: ");
    }

    public void shipping_address_created_successfully() {
        System.out.println("Shipping address created successfully!");
    }

    public void enter_shipping_address_number() {
        System.out.println("Enter shipping address number: ");
    }

    public void shipping_address_does_not_exist() {
        System.out.println("Shipping address does not exist!");
    }

    public void shipping_address_updated_successfully() {
        System.out.println("Shipping address updated successfully!");
    }

    public void shipping_address_deleted_successfully() {
        System.out.println("Shipping address deleted successfully!");
    }

    public void all_shipping_addresses_deleted_successfully() {
        System.out.println("All shipping addresses deleted successfully!");
    }

    public void view_all_orders(List<Order> orders) {
        System.out.println(orders);
    }

    public void enter_order_id() {
        System.out.println("Enter order id: ");
    }

    public void print_order(Order order) {
        System.out.println(order);
    }


    public void add_to_cart() {
        System.out.println("--------------------------\nAdd to cart\n--------------------------\n" +
                "Structure: <product id>&<quantity>");
    }

    public void product_Added_to_cart_successully() {
        System.out.println("Product added to cart successfully!");
    }

    public void view_all_users() {
        System.out.println("-------------------------\nHere are all the users\n-------------------------\n");
    }

    public void list_all_users(User user) {
        System.out.println(user);
    }

    public void edit_user() {
        System.out.println("----------------------\nEdit user\n----------------------");
    }

    public void enter_user_email() {
        System.out.println("Enter user id: ");
    }

    public void remove_user() {
        System.out.println("----------------------\nRemove user\n----------------------");
    }

    public void not_found() {
        System.out.println("Not found!");
    }
    public void bad_request() {
        System.out.println("Bad request!");
    }

    public void internal_server_error() {
        System.out.println("Internal server error!");
    }

    public void product_already_exists() {
        System.out.println("Product already exists");
    }

    public void shopping_cart_options() {
        System.out.println("0. Back\n" +
                "1. Create order");
    }

    public void cashOrCard() {
        System.out.println("Cash or card? \n >>>");
    }
}
