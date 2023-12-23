package com.online_shop.MAP_Labor_2_Spring.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online_shop.MAP_Labor_2_Spring.interfaces.UserObserver;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "shipping_addresses")
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String name;
    private String phone;
    private String address;
    private String city;
    private String zipcode;

//    @Override
//    public void update(User user) {
//        setUser(user);
//    }
}

//    private List<Order> orders = new ArrayList<>();

//    @Override
//    public void update(String firstname, String lastname, String email, String password, ShoppingCart shoppingCart) {
//        this.user.setFirstname(firstname);
//        this.user.setLastname(lastname);
//        this.user.setEmail(email);
//        this.user.setPassword(password);
//     //   this.user.set$shoppingCart(shoppingCart);
//    }
