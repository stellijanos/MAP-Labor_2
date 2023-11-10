package org.online_shop.controllers;

import org.online_shop.enums.Response;
import org.online_shop.models.ShoppingCart;
import org.online_shop.repositories.ShoppingCartRepository;


public class ShoppingCartController {
    private ShoppingCartRepository _shoppingCartRepository;

    public ShoppingCartController(ShoppingCartRepository shoppingCartRepository) {
        this._shoppingCartRepository = shoppingCartRepository;
    }

    public Response createShoppingCart(int userId) {
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.set_userId(userId);
        shoppingCart.set_id(_shoppingCartRepository.totalNumber() + 1);

        return _shoppingCartRepository.create(shoppingCart) ? Response.SHOPPING_CART_CREATE_SUCCESSFUL :
                Response.SOMETHING_WENT_WRONG;
    }

    public Response deleteShoppingCart(int userId) {
        ShoppingCart shoppingCart = _shoppingCartRepository.read(userId);
        return shoppingCart.get_userId() == null ? Response.SHOPPING_CART_DOES_NOT_EXIST :
                _shoppingCartRepository.delete(shoppingCart) ? Response.SHOPPING_CART_DELETE_SUCCESSFUL :
                        Response.SOMETHING_WENT_WRONG;
    }
}
