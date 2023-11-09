package org.online_shop.controllers;

import org.online_shop.enums.Response;
import org.online_shop.models.Favourite;
import org.online_shop.repositories.FavouriteRepository;

import java.util.List;

public class FavouriteController {
    private final FavouriteRepository _favouriteRepository;

    public FavouriteController(FavouriteRepository favouriteRepository) {
        _favouriteRepository = favouriteRepository;
    }

    public Response addOrRemove(Integer userId, Integer productId) {
        Favourite favourite = new Favourite(userId, productId);

        if (_favouriteRepository.readAll(userId).contains(favourite)) {
            _favouriteRepository.readAll(userId).remove(favourite);
            return Response.PRODUCT_REMOVED_FROM_FAVOURITES;
        }
        _favouriteRepository.readAll(userId).add(favourite);
        return Response.PRODUCT_ADDED_TO_FAVOURITES;
    }
    public List<Favourite> viewAll(Integer userId) {
        return _favouriteRepository.readAll(userId);
    }
}
