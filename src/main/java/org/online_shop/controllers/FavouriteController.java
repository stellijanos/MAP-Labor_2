package org.online_shop.controllers;

import org.online_shop.enums.Response;
import org.online_shop.models.Favourite;
import org.online_shop.models.Product;
import org.online_shop.models.User;
import org.online_shop.repositories.FavouriteRepository;

import java.util.List;

public class FavouriteController {
    private final FavouriteRepository _favouriteRepository;

    public FavouriteController(FavouriteRepository favouriteRepository) {
        _favouriteRepository = favouriteRepository;
    }

    public Response addOrRemove(User user, Product product) {
        Favourite favourite = new Favourite();
        favourite.set_user(user);
        favourite.set_product(product);

        if (_favouriteRepository.readAll(user).contains(favourite)) {
            _favouriteRepository.delete(favourite);
            return Response.PRODUCT_REMOVE_FROM_FAVOURITES;
        }
        _favouriteRepository.create(favourite);
        return Response.PRODUCT_ADD_TO_FAVOURITES;
    }
    public List<Favourite> viewAll(User user) {
        return _favouriteRepository.readAll(user);
    }
}
