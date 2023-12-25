package com.online_shop.MAP_Labor_2_Spring.services;

import com.online_shop.MAP_Labor_2_Spring.models.Product;
import com.online_shop.MAP_Labor_2_Spring.repositories.ProductRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FavouriteService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public FavouriteService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<Iterable<Product>> getFavouriteProducts(Long user_id) {
        return userRepository.findById(user_id)
                .map(user -> ResponseEntity.ok((Iterable<Product>) user.getFavourites()))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Product> addOrRemoveFavourite(Long user_id, Long product_id) {
        return userRepository.findById(user_id)
                .map(user -> productRepository.findById(product_id)
                        .map(product -> {
                            if (user.getFavourites().contains(product)) user.removeFromFavourites(product);
                            else user.addToFavourites(product);
                            userRepository.save(user);
                            return ResponseEntity.ok(product);
                        }).orElse(ResponseEntity.notFound().build())
                ).orElse(ResponseEntity.notFound().build());
    }
}
