package com.online_shop.MAP_Labor_2_Spring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online_shop.MAP_Labor_2_Spring.interfaces.Subject;
import com.online_shop.MAP_Labor_2_Spring.interfaces.UserObserver;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
@Entity
@ToString
@Table(name = "users")
public class User /*implements Subject*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @Column(columnDefinition = "DATETIME")
    @CreationTimestamp
    private LocalDateTime created_at;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<ShippingAddress> shippingAddresses = new HashSet<>();

//    @JsonIgnore
//    private final List<UserObserver> observers = new ArrayList<>();
//
//    @Override
//    public void add(UserObserver observer) {
//        this.observers.add(observer);
//    }
//
//    @Override
//    public void remove(UserObserver observer) {
//        this.observers.remove(observer);
//    }
//
//    @Override
//    public void notifyObservers() {
//        for (UserObserver userObserver: observers) {
//            userObserver.update(this);
//        }
//    }
}


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String firstname;
//    private String lastname;
//    private String email;
//    private String password;

//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//private ShoppingCart $shoppingCart;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private final List<UserObserver> $observers = new ArrayList<>();
//    @ManyToMany
//    @JoinTable(
//            name = "favourites",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private final List<Product> $favourites = new ArrayList<>();
//
//    public void add$favourites(Product product) {
//        $favourites.add(product);
//    }
//
//    public void remove$favourites(Product product) {
//        $favourites.remove(product);
//    }
//
