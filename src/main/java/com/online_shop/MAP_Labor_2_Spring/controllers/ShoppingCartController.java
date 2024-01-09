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

    /**
     * Endpoint to retrieve the shopping cart of a specific user.
     * Handles GET requests to retrieve the shopping cart by user_id.
     *
     * @param user_id ID of the user whose shopping cart is to be retrieved
     * @return ResponseEntity containing the user's shopping cart information
     */
    @GetMapping("/{user_id}/shopping_cart")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable Long user_id) {
        return shoppingCartService.getShoppingCart(user_id);
    }

    /**
     * Endpoint to delete the shopping cart of a specific user.
     * Handles DELETE requests to delete the shopping cart by user_id.
     *
     * @param user_id ID of the user whose shopping cart is to be deleted
     * @return ResponseEntity indicating the deletion status
     */
    @DeleteMapping("/{user_id}/shopping_cart")
    public ResponseEntity<String> deleteShoppingCart(@PathVariable Long user_id) {
        return shoppingCartService.deleteShoppingCart(user_id);
    }

    /**
     * Endpoint to add items to the shopping cart of a specific user.
     * Handles POST requests to add items to the shopping cart by user_id, product_id, and quantity.
     *
     * @param user_id ID of the user whose shopping cart is updated
     * @param product_id ID of the product to add to the shopping cart
     * @param quantity Quantity of the product to add to the shopping cart
     * @return ResponseEntity containing the added shopping cart item information
     */
    @PostMapping("/{user_id}/shopping_cart/items/{product_id}/{quantity}")
    public ResponseEntity<ShoppingCartItem> addToShoppingCart(
            @PathVariable Long user_id,
            @PathVariable Long product_id,
            @PathVariable Integer quantity) {
        return shoppingCartService.addToShoppingCart(user_id, product_id, quantity);
    }

}
