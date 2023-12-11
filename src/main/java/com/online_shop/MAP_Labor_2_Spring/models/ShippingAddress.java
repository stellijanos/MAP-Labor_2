//package com.online_shop.MAP_Labor_2_Spring.models;
//
//
//import com.online_shop.MAP_Labor_2_Spring.interfaces.UserObserver;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Setter
//@ToString
//@Getter
//@Table(name = "shipping_addresses")
//public class ShippingAddress implements UserObserver {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private User user;
//    private String name;
//    private String phone;
//    private String address;
//    private String city;
//    private String zipCode;
//    private List<Order> orders = new ArrayList<>();
//
//    @Override
//    public void update(String firstname, String lastname, String email, String password, ShoppingCart shoppingCart) {
//        this.user.setFirstname(firstname);
//        this.user.setLastname(lastname);
//        this.user.setEmail(email);
//        this.user.setPassword(password);
//     //   this.user.set$shoppingCart(shoppingCart);
//    }
//}
