package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.Favourite;
import org.online_shop.models.Product;
import org.online_shop.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class FavouriteRepository extends DatabaseInMemory {
    public boolean create(Favourite favourite) {
        return _favourites.add(favourite);
    }

    public List<Favourite> readAll(User user) {
        return _favourites.stream().filter(favourite -> favourite.get_user().get_id().equals(user.get_id())).collect(Collectors.toList());
    }

    public boolean delete(Favourite favourite) {
        return _favourites.remove(favourite);
    }

    public boolean deleteAll() {
        _favourites.clear();
        return true;
    }

    public void addToFavourites(Favourite favourite) {
        _favourites.add(favourite);
    }

    public void removeFromFavourites() {
//        _user.
    }

}
