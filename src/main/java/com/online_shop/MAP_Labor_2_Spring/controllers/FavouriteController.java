package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.Product;
import com.online_shop.MAP_Labor_2_Spring.services.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class FavouriteController {


    private final FavouriteService favouriteService;

    @Autowired
    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    /**
     * Endpoint to retrieve all favorite products of a specific user.
     * Handles GET requests to retrieve all favorite products of a user by user_id.
     *
     * @param user_id ID of the user whose favorite products are to be retrieved
     * @return ResponseEntity containing a list of favorite products associated with the user
     */
    @GetMapping("/{user_id}/favourites")
    public ResponseEntity<Iterable<Product>> getAllFavouriteProducts(@PathVariable Long user_id) {
        return favouriteService.getFavouriteProducts(user_id);
    }

    /**
     * Endpoint to add or remove a product from user's favorites.
     * Handles POST requests to add or remove a product from favorites by user_id and product_id.
     *
     * @param user_id ID of the user whose favorite products are to be modified
     * @param product_id ID of the product to be added or removed from favorites
     * @return ResponseEntity containing the updated product information
     */
    @PostMapping("/{user_id}/favourites/{product_id}")
    public ResponseEntity<Product> addOrRemoveFavourite(@PathVariable Long user_id, @PathVariable Long product_id) {
        return favouriteService.addOrRemoveFavourite(user_id, product_id);
    }

}
