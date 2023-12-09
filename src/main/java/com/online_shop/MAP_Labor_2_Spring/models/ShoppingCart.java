package com.online_shop.MAP_Labor_2_Spring.models;

import com.online_shop.MAP_Labor_2_Spring.interfaces.UserObserver;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString
public class ShoppingCart implements UserObserver {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private final List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    @Override
    public void update(String firstname, String lastname, String email, String password, ShoppingCart shoppingCart) {
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.set$shoppingCart(shoppingCart);
    }
}
