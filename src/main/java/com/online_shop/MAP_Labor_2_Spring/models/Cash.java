//package com.online_shop.MAP_Labor_2_Spring.models;
//
//import com.online_shop.MAP_Labor_2_Spring.interfaces.PaymentStrategy;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.ToString;
//
//@Entity
//@ToString
//@Table(name = "payment")
//public class Cash implements PaymentStrategy {
//
//    @Id
//    @Getter
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private final String type = "card";
//
//    @Override
//    public void pay(Float amount) {
//    }
//}
