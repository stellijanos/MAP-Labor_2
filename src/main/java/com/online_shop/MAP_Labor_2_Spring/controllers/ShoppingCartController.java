package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCart;
import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCartItem;
import com.online_shop.MAP_Labor_2_Spring.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/users")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/{user_id}/shopping_cart")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable Long user_id) {
        return shoppingCartService.getShoppingCart(user_id);
    }

    @DeleteMapping("/{user_id}/shopping_cart")
    public ResponseEntity<String> deleteShoppingCart(@PathVariable Long user_id) {
        return shoppingCartService.deleteShoppingCart(user_id);
    }

    @PostMapping("/{user_id}/shopping_cart/items/{product_id}/{quantity}")
    public ResponseEntity<ShoppingCartItem> addToShoppingCart(
            @PathVariable Long user_id,
            @PathVariable Long product_id,
            @PathVariable Integer quantity) {
        return shoppingCartService.addToShoppingCart(user_id, product_id, quantity);
    }

}
