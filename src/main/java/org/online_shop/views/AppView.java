package org.online_shop.views;

import org.online_shop.models.Category;
import org.online_shop.models.Product;
import org.online_shop.models.User;

import java.sql.SQLOutput;
import java.util.List;

public class AppView {
    public String enterOption() {
        return "\n\nEnter option please:\n";
    }

    public void mainMenu() {
        System.out.println("\n------------------------------------------------\n" +
                "@author Janos Stelli" +
                "\n------------------------------------------------\n" +
                "Welcome to the online shop" +
                "\n------------------------------------------------\n" +
                "Options:\n" +
                "0. Quit App\n" +
                "1. LogIn\n" +
                "2. SignUp" +
                enterOption()
        );
    }

    public void option_not_found() {
        System.out.println("!!!!!!!!!!!!!!! Option not found, please try again! !!!!!!!!!!!!!!!\n");
    }

    public void print_goodBye() {
        System.out.println("Goodbye, see you next time!\n---------------------\n");
    }

    public void userPanel() {
        System.out.println("\n------------------------------\n" +
                "User Panel\n------------------------------\n" +
                "0. LogOut\n" +
                "1. Account settings\n" +
                "2. Shipping Address options\n" +
                "3. Orders\n" +
                "4. Favourites\n" +
                "5. ShoppingCart\n" +
                "6. Products" +
                enterOption()
        );
    }

    // userPanel option 1
    public void accountSettings() {
        System.out.println("\n------------------------------\n" +
                "Account settings\n------------------------------\n" +
                "0. Back\n" +
                "1. View profile details\n" +
                "2. Edit profile details\n" +
                "3. Change password\n" +
                "4. Delete account" +
                enterOption()
        );
    }

    // userPanel option 2
    public void shippingAddressOptions() {
        System.out.println("\n------------------------------\n" +
                "Shipping Address options\n------------------------------\n" +
                "0. Back\n" +
                "1. View saved addresses\n" +
                "2. Add address\n" +
                "3. Edit address\n" +
                "4. Delete address\n" +
                "5. Delete all addresses" +
                enterOption()
        );
    }

    // userPanel option 3
    public void orders() {
        System.out.println("\n------------------------------\n" +
                "Shipping Address options\n------------------------------\n" +
                "0. Back\n" +
                "1. View all orders\n" +
                "2. View order\n" +
                "Enter option: "
        );
    }

    // userPanel option 4

    public void favourites() {
        System.out.println("\n---------------------\n" +
                "Shopping cart" +
                "\n---------------------\n" +
                "Options:" +
                "0. Back" +
                "\n---------------------\n");
    }

    // userPanel option 5

    public void shoppingCart() {
        System.out.println("\n---------------------\n" +
                "Shopping cart" +
                "\n---------------------\n" +
                "Options:" +
                "0. Back" +
                "\n---------------------\n");
    }

    // userPanel option 6

    public void products() {
        System.out.println("\n---------------------\n" +
                "Products" +
                "\n---------------------\n" +
                "Options:" +
                "0. Back" +
                "1. Add To Favourites" +
                "2. Add To Cart" +
                "\n---------------------\n");
    }

    public void print_adminOption() {
        System.out.println("9. Admin Panel");
    }

    public void adminPanel() {
        System.out.println("\n------------------------------\n" +
                "Admin Panel\n------------------------------\n" +
                "0. Back\n" +
                "1. User options\n" +
                "2. Product options\n" +
                "3. Order options" +
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
        System.out.printf("\n-------------------\n Account details\n-------------------\n" +
                        "Member since: %s\nFirstname:    %s\nLastname:     %s\nEmail:        %s\n",
                user.get_createdAt(), user.get_firstname(), user.get_lastname(), user.get_email());
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
}
