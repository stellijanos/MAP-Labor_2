package org.online_shop.views;

import org.online_shop.models.ShoppingCart;

import java.util.List;

public class ShoppingCartView {
    public void view(ShoppingCart shoppingCart) {
        System.out.println(shoppingCart);
    }

    public void viewAll(List<ShoppingCart> shoppingCarts) {
        for (ShoppingCart shoppinCart:shoppingCarts) {
            System.out.println(shoppinCart);
        }
    }
}
