package org.online_shop.views;

import org.online_shop.models.Favourite;

import java.util.List;

public class FavouriteView {
    public void view(Favourite favourite) {
        System.out.println(favourite);
    }

    public void viewAll(List<Favourite> favourites) {
        for (Favourite favourite:favourites) {
            System.out.println(favourite);
        }
    }
}
