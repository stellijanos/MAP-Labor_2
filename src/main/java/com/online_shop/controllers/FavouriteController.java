package org.online_shop.controllers;

import org.online_shop.enums.Response;
import org.online_shop.models.Favourite;
import org.online_shop.models.Product;
import org.online_shop.models.User;
import org.online_shop.repositories.FavouriteRepository;
import org.online_shop.repositories.ProductRepository;
import org.online_shop.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class FavouriteController {
    private final FavouriteRepository _favouriteRepository;
    private final ProductRepository productRepository = new ProductRepository();
    private final UserRepository userRepository = new UserRepository();

    public FavouriteController(FavouriteRepository favouriteRepository) {
        _favouriteRepository = favouriteRepository;
    }

    public Response addOrRemove(User user, Product product) {
        Favourite favourite = new Favourite(user, product);

        if (_favouriteRepository.readAll(user).contains(favourite)) {
            _favouriteRepository.delete(favourite);
            return Response.PRODUCT_REMOVE_FROM_FAVOURITES;
        }
        _favouriteRepository.create(favourite);
        return Response.PRODUCT_ADD_TO_FAVOURITES;
    }

    public List<Product> viewAll(User user) {
        return _favouriteRepository.readAll(user);
    }


    public Response addOrRemoveToFavourites(String email, Integer productId) {

        User user = userRepository.read(email);

        Product product = _favouriteRepository.read(productId, user);
        Favourite favourite = new Favourite(user, product);

        return product.getName().isEmpty() ? _favouriteRepository.create(favourite) ? Response.PRODUCT_ADD_TO_FAVOURITES : Response.SOMETHING_WENT_WRONG
                : _favouriteRepository.delete(favourite) ? Response.PRODUCT_REMOVE_FROM_FAVOURITES : Response.SOMETHING_WENT_WRONG;
    }
}
