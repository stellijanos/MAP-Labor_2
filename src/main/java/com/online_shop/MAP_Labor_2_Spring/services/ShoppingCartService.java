package com.online_shop.MAP_Labor_2_Spring.services;

import com.online_shop.MAP_Labor_2_Spring.models.Product;
import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCart;
import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCartItem;
import com.online_shop.MAP_Labor_2_Spring.models.User;
import com.online_shop.MAP_Labor_2_Spring.repositories.ProductRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.ShoppingCartItemRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.ShoppingCartRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository,
                               ShoppingCartItemRepository shoppingCartItemRepository,
                               UserRepository userRepository,
                               ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<ShoppingCart> getShoppingCart(Long user_id) {
        if (!userRepository.existsById(user_id))
            return ResponseEntity.notFound().build();
        return shoppingCartRepository.findByUserId(user_id).map(
                cart -> ResponseEntity.ok().body(cart)
        ).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> deleteShoppingCart(Long user_id) {
        if (!userRepository.existsById(user_id) || !shoppingCartRepository.existsByUserId(user_id))
            return ResponseEntity.notFound().build();
        shoppingCartRepository.deleteByUserId(user_id);
        return ResponseEntity.ok("Shopping cart deleted successfully!");
    }

    public ResponseEntity<ShoppingCartItem> addToShoppingCart(Long user_id, Long product_id, Integer quantity) {

        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isEmpty()) return ResponseEntity.notFound().build();

        Optional<Product> productOptional = productRepository.findById(product_id);
        if (productOptional.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOptional.get();
        Product product = productOptional.get();

        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByUserId(user_id);
        ShoppingCart shoppingCart;

        if (shoppingCartOptional.isEmpty()) {
            ShoppingCart newCart = new ShoppingCart();
            newCart.setUser(user);
            shoppingCart = shoppingCartRepository.save(newCart);
        } else {
            shoppingCart = shoppingCartOptional.get();
        }

        Optional<ShoppingCartItem> shoppingCartItemOptional = shoppingCartItemRepository.findByShoppingCartIdAndProductId(shoppingCart.getId(), product_id);

        if (shoppingCartItemOptional.isEmpty()) {
            ShoppingCartItem newItem = new ShoppingCartItem();
            newItem.setShoppingCart(shoppingCart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            return ResponseEntity.ok(shoppingCartItemRepository.save(newItem));
        }

        ShoppingCartItem item = shoppingCartItemOptional.get();
        item.setQuantity(quantity);
        return ResponseEntity.ok(shoppingCartItemRepository.save(item));
    }


    public ResponseEntity<String> deleteShoppingCartItem(Long user_id, Long product_id) {
        return userRepository.findById(user_id)
                .map(user -> shoppingCartRepository.findByUserId(user_id)
                        .map(cart -> shoppingCartItemRepository.findByShoppingCartIdAndProductId(cart.getId(), product_id)
                                .map(item -> {
                                    shoppingCartItemRepository.delete(item);
                                    return ResponseEntity.ok("Shopping cart item deleted successfully!");
                                }).orElse(ResponseEntity.notFound().build()))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }
}
