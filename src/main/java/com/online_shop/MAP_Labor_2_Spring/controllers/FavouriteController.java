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

    @GetMapping("/{user_id}/favourites")
    public ResponseEntity<Iterable<Product>> getAllFavouriteProducts(@PathVariable Long user_id) {
        return favouriteService.getFavouriteProducts(user_id);
    }

    @PostMapping("/{user_id}/favourites/{product_id}")
    public ResponseEntity<Product> addOrRemoveFavourite(@PathVariable Long user_id, @PathVariable Long product_id) {
        return favouriteService.addOrRemoveFavourite(user_id, product_id);
    }

}
