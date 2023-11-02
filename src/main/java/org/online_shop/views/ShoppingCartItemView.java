package org.online_shop.views;

import org.online_shop.models.ShoppingCartItem;

import java.util.List;

public class ShoppingCartItemView {
    public void view(ShoppingCartItem shoppingCartItem) {
        System.out.println(shoppingCartItem);
    }

    public void viewAll(List<ShoppingCartItem> shoppingCartItems) {
        for (ShoppingCartItem shoppingCartItem:shoppingCartItems) {
            System.out.println(shoppingCartItem);
        }
    }
}
