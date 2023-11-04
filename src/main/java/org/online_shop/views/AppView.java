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
}
