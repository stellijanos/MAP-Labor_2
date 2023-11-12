package org.online_shop;

import org.online_shop.controllers.AppController;

public class Main {
    public static void main(String[] args) {

        AppController appController = new AppController();

        appController.setRoute("", appController::quit);
        appController.setRoute("/", appController::mainMenu);
        appController.setRoute("/login", appController::logIn);
        appController.setRoute("/signup", appController::signUp);
        appController.setRoute("/logout", appController::logOut);
        appController.setRoute("/user-panel", appController::userPanel);
        appController.setRoute("/account-settings", appController::accountSettings);
        appController.setRoute("/account-settings/profile-details", appController::accountSettings);
        appController.setRoute("/shipping-address-options", appController::shippingAddressOptions);
        appController.setRoute("/orders", appController::orders);
        appController.setRoute("/favourites", appController::favourites);
        appController.setRoute("/shopping-cart", appController::shoppingCart);
        appController.setRoute("/products", appController::products);




        appController.setRoute("/admin-panel", appController::adminPanel);

        appController.run();
    }
}
