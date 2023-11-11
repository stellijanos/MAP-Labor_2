package org.online_shop.views;

public class AppView {
    public final CategoryView categoryView = new CategoryView();
    public final FavouriteView favouriteView = new FavouriteView();
    public final OrderItemView orderItemView = new OrderItemView();
    public final OrderView orderView = new OrderView();
    public final ProductSpecView productSpecView = new ProductSpecView();
    public final ProductView productView = new ProductView();
    public final ShippingAddressView shippingAddressView = new ShippingAddressView();
    public final ShoppingCartItemView shoppingCartItemView = new ShoppingCartItemView();
    public final ShoppingCartView shoppingCartView = new ShoppingCartView();
    public final UserView userView = new UserView();

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

    public void print_optionNotFound() {
        System.out.println("!!!!!!!!!!!!!!! Option not found, please try again! !!!!!!!!!!!!!!!\n");
    }

    public void print_goodBye() {
        System.out.println("Goodbye, see you next time!\n---------------------\n");
    }

    public void userPanel() {
        System.out.println("\n------------------------------\n" +
                "User Menu\n------------------------------\n" +
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
                "1. View all addresses\n" +
                "2. Add address\n" +
                "3. Edit address\n" +
                "4. Delete address" +
                enterOption()
        );
    }

    // userPanel option 3
    public void orders() {
        System.out.println("\n------------------------------\n" +
                "Shipping Address options\n------------------------------\n" +
                "0. Back\n" +
                "1. View all addresses\n" +
                "2. Add address\n" +
                "3. Edit address\n" +
                "4. Delete address\n" +
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
                "\n---------------------\n");
    }

    public void print_adminOption() {
        System.out.println("9. Admin Panel");
    }

    public void print_adminPanel() {
        System.out.println("\n------------------------------\n" +
                "Admin Panel\n------------------------------\n" +
                "0. Back\n" +
                "1. User option\n" +
                "2. Product options\n" +
                "3. Order options\n" +
                "Enter option: "
        );
    }

    public void print_back() {
        System.out.println("0. Back");
    }
}
