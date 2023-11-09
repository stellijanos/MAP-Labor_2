package org.online_shop.controllers;

import org.online_shop.repositories.FavouriteRepository;

public class FavouriteController {
    private FavouriteRepository _favouriteRepository;

    public FavouriteController(FavouriteRepository favouriteRepository) {
        _favouriteRepository = favouriteRepository;
    }
}
