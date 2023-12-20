package com.online_shop.MAP_Labor_2_Spring.models;

import com.online_shop.MAP_Labor_2_Spring.interfaces.UserObserver;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@ToString
@Table(name = "shopping_carts")
public class ShoppingCart /* implements UserObserver*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private Set<ShoppingCartItem> shoppingCartItems = new HashSet<>();

//    @Override
//    public void update(String firstname, String lastname, String email, String password, ShoppingCart shoppingCart) {
//        user.setFirstname(firstname);
//        user.setLastname(lastname);
//        user.setEmail(email);
//        user.setPassword(password);
//        //user.set$shoppingCart(shoppingCart);
//    }
}
