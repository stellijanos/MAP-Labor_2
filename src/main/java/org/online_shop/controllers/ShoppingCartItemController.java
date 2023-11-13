package org.online_shop.controllers;

import org.online_shop.enums.Response;
import org.online_shop.models.ShoppingCart;
import org.online_shop.models.ShoppingCartItem;
import org.online_shop.repositories.ShoppingCartItemRepository;

import java.util.List;

public class ShoppingCartItemController {
    private final ShoppingCartItemRepository _shoppingCartItemRepository;

    public ShoppingCartItemController(ShoppingCartItemRepository shoppingCartItemRepository) {
        this._shoppingCartItemRepository = shoppingCartItemRepository;
    }

    public List<ShoppingCartItem> getAll(ShoppingCart shoppingCart) {
        return this._shoppingCartItemRepository.readAll(shoppingCart);
    }

    public Response addToShoppingCart(Integer shoppingCartId, Integer productId, Integer quantity) {
        return Response.SOMETHING_WENT_WRONG;

    }
//        ShoppingCartItem shoppingCartItem = _shoppingCartItemRepository.readAll(shoppingCartId, productId);

//        if (shoppingCartItem.get_shoppingCartId() == null) {
//            ShoppingCartItem newItem = new ShoppingCartItem();
//            newItem.set_shoppingCartId(shoppingCartId);
//            newItem.set_productId(productId);
//            newItem.set_quantity(1);
//
//            return _shoppingCartItemRepository.create(newItem) ? Response.PRODUCT_ADD_TO_CART : Response.SOMETHING_WENT_WRONG;
//
//        }
//        return shoppingCartItem.set_quantity(quantity.equals(Integer.MAX_VALUE) ? shoppingCartItem.get_quantity() : quantity ) ?
//                Response.PRODUCT_ADD_TO_CART : Response.SOMETHING_WENT_WRONG;
//    }

    }
