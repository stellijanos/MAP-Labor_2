package com.online_shop.MAP_Labor_2_Spring.models;

import com.online_shop.MAP_Labor_2_Spring.interfaces.PaymentStrategy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Card implements PaymentStrategy {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id", referencedColumnName = "id")
    private User user;
    private String type;
    private String name;
    private String card_number;
    private Integer cvv;
    private Integer expiration_month;
    private Integer expiration_year;


    @Override
    public void processPayment() {

    }
}
