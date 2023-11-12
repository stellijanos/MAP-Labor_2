package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.Favourite;

import java.util.List;
import java.util.stream.Collectors;

public class FavouriteRepository extends DatabaseInMemory {
    public boolean create(Favourite favourite) {
        return _favourites.add(favourite);
    }

    public List<Favourite> readAll(Integer userId) {
        return _favourites.stream().filter(favourite -> favourite.get_user().get_id().equals(userId)).collect(Collectors.toList());
    }

    public boolean delete(Favourite favourite) {
        return _favourites.remove(favourite);
    }

    public boolean deleteAll() {
        _favourites.clear();
        return true;
    }
}
