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
        return "\n\nEnter option please:";
    }

    public void logIn_signUp() {
        System.out.println("\n------------------------------------------------\n" +
                "@author Janos Stelli" +
                "\n------------------------------------------------\n" +
                "Welcome to the online shop" +
                "\n------------------------------------------------\n" +
                "Options:\n" +
                "0. Quit App\n" +
                "1. LogIn\n" +
                "2. SignUp" +
                enterOption());
    }

    public void optionNotFound() {
        System.out.println("!!!!!!!!!!!!!!! Option not found, please try again! !!!!!!!!!!!!!!!\n");
    }

    public void goodBye() {
        System.out.println("Goodbye, see you next time!\n---------------------\n");
    }


    public void userPanel() {
        System.out.println("\n------------------------------\n    User Menu\n------------------------------\n" +
                "0. LogOut" +
                "1. Account details" +
                "2. Update Account Info" +
                "3. Change Password" +
                "4. Delete Account" +
                "5. Favourites" +
                "6. Shopping Cart" +
                "7. Search product" +
                "8. View all products"
        );
    }

    public void adminOption() {
        System.out.println("9. Admin Panel");
    }

    public void adminPanel() {
        System.out.println("\n------------------------------\n     Admin Panel\n------------------------------\n" +
                "1. View all users" +
                "2. Remove user" +
                "3. View all products" +
                "4. Search product" +
                "5. Add product" +
                "6. Update product" +
                "7. Delete product"
        );
    }
}
