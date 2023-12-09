package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.enums.Response;
import org.online_shop.models.ShoppingCart;
import org.online_shop.models.User;
import com.online_shop.MAP_Labor_2_Spring.models.repositories.ShoppingCartRepository;


public class ShoppingCartController {
    private final ShoppingCartRepository _shoppingCartRepository;

    public ShoppingCartController(ShoppingCartRepository shoppingCartRepository) {
        this._shoppingCartRepository = shoppingCartRepository;
    }

    public Response createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setUser(user);
//        shoppingCart.setId(_shoppingCartRepository.totalNumber() + 1);

        return _shoppingCartRepository.create(shoppingCart) ? Response.SHOPPING_CART_CREATE_SUCCESSFUL :
                Response.SOMETHING_WENT_WRONG;
    }

    public ShoppingCart get(User user) {
        return _shoppingCartRepository.read(user);
    }


    public Response deleteShoppingCart(User user) {
        ShoppingCart shoppingCart = _shoppingCartRepository.read(user);
        return shoppingCart.getUser().getEmail() == null ? Response.SHOPPING_CART_DOES_NOT_EXIST :
                _shoppingCartRepository.delete(shoppingCart) ? Response.SHOPPING_CART_DELETE_SUCCESSFUL :
                        Response.SOMETHING_WENT_WRONG;
    }
}
