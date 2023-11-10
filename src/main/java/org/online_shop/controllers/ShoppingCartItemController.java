package org.online_shop.controllers;

import org.online_shop.enums.Response;
import org.online_shop.models.ShoppingCartItem;
import org.online_shop.repositories.ShoppingCartItemRepository;

import java.util.List;

public class ShoppingCartItemController {
    private ShoppingCartItemRepository _shoppingCartItemRepository;

    public ShoppingCartItemController(ShoppingCartItemRepository shoppingCartItemRepository) {
        this._shoppingCartItemRepository = shoppingCartItemRepository;
    }

    public List<ShoppingCartItem> getAll(Integer shoppingCartId) {
        return this._shoppingCartItemRepository.readAll(shoppingCartId);
    }

    public Response addToShoppingCart

}
