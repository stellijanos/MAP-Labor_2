package org.online_shop.controllers;

import org.online_shop.repositories.ShoppingCartItemRepository;

public class ShoppingCartItemController {
    private ShoppingCartItemRepository _shoppingCartItemRepository;

    public ShoppingCartItemController(ShoppingCartItemRepository shoppingCartItemRepository) {
        this._shoppingCartItemRepository = shoppingCartItemRepository;
    }
}
