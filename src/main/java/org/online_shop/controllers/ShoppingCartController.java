package org.online_shop.controllers;

import org.online_shop.repositories.ShoppingCartRepository;

public class ShoppingCartController {
    private ShoppingCartRepository _shoppingCartRepository;

    public ShoppingCartController(ShoppingCartRepository shoppingCartRepository) {
        this._shoppingCartRepository = shoppingCartRepository;
    }
}
