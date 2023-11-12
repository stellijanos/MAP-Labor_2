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
        appController.setRoute("/account-settings/profile-details", appController::profileDetails);
        appController.setRoute("/account-settings/edit-profile-details", appController::editProfileDetails);
        appController.setRoute("/account-settings/change-password", appController::changePassword);
        appController.setRoute("/account-settings/delete-account", appController::deleteAccount);

        appController.setRoute("/shipping-address-options", appController::shippingAddressOptions);
        appController.setRoute("/shipping-address-options/view-saved-addresses", appController::viewSavedAddresses);
        appController.setRoute("/shipping-address-options/add-address", appController::addAddress);
        appController.setRoute("/shipping-address-options/edit-address", appController::editAddress);
        appController.setRoute("/shipping-address-options/delete-address", appController::deleteAddress);
        appController.setRoute("/shipping-address-options/delete-all-addresses", appController::deleteAllAddresses);

        appController.setRoute("/orders", appController::orders);
        appController.setRoute("/orders/view-all-orders", appController::viewAllOrders);
        appController.setRoute("/orders/view-order", appController::viewOrder);

        appController.setRoute("/favourites", appController::favourites);

        appController.setRoute("/shopping-cart", appController::shoppingCart);

        appController.setRoute("/products", appController::products);
        appController.setRoute("/products/add-to-favourites", appController::addToFavourites);
        appController.setRoute("/products/add-to-cart", appController::addToCart);

        appController.setRoute("/admin-panel", appController::adminPanel);
        appController.setRoute("/user-options", appController::userOptions);
        appController.setRoute("/view-all-users", appController::viewAllUsers);
        appController.setRoute("/edit-user", appController::editUser);
        appController.setRoute("/remove-user", appController::removeUser);
        appController.setRoute("/remove-all-users", appController::removeAllUsers);

        appController.setRoute("/product-options", appController::productOptions);
        appController.setRoute("/view-all-products", appController::viewAllProducts);
        appController.setRoute("/add-product", appController::addProduct);
        appController.setRoute("/edit-product", appController::editProduct);
        appController.setRoute("/remove-product", appController::removeProduct);
        appController.setRoute("/remove-all-products", appController::removeAllProducts);

        appController.setRoute("/order-options", appController::orderOptions);
        appController.setRoute("/view-all-users-orders", appController::viewAllUsersOrders);
        appController.setRoute("/edit order", appController::editOrder);
        appController.setRoute("/remove-order", appController::removeOrder);
        appController.setRoute("/remove-all-orders", appController::removeAllOrders);


        appController.run();
    }
}
