//package com.online_shop.MAP_Labor_2_Spring.models;
//
//import com.online_shop.MAP_Labor_2_Spring.interfaces.Subject;
//import com.online_shop.MAP_Labor_2_Spring.interfaces.UserObserver;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//@Entity
//@ToString
//@Table(name = "users")
//public class User implements Subject {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private String firstname;
//    private String lastname;
//    private String email;
//    private String password;
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private ShoppingCart $shoppingCart;
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
//    @Override
//    public void add(UserObserver observer) {
//        $observers.add(observer);
//    }
//
//    @Override
//    public void remove(UserObserver observer) {
//        $observers.remove(observer);
//    }
//
//    @Override
//    public void notifyObservers() {
//        for (UserObserver observer : $observers) {
//            observer.update(firstname, lastname, email, password, $shoppingCart);
//        }
//    }
//}
