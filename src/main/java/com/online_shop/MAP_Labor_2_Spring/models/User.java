package com.online_shop.MAP_Labor_2_Spring.models;


import com.online_shop.MAP_Labor_2_Spring.interfaces.UserObserver;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    private String firstname;
    @Getter
    @Setter
    private String lastname;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;

    @Setter
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    @Getter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final List<UserObserver> observers = new ArrayList<>();

    @Getter
    @ManyToMany
    @JoinTable(
            name = "favourites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private final List<Product> favourites = new ArrayList<>();


    public String getCreatedAt() {
        return createdAt;
    }

    public void unsetShoppingCart() {
        this.shoppingCart = null;
    }

    public void setFavourite(Product product) {
        favourites.add(product);
    }

    public void removeFavourite(Product product) {
        favourites.remove(product);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", shoppingCart=" + shoppingCart +
                '}';
    }

    @Override
    public void add(UserObserver observer) {
        observers.add(observer);
    }

    @Override
    public void remove(UserObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (UserObserver observer : observers) {
            observer.update(firstname, lastname, email, password, shoppingCart);
        }
    }
}
