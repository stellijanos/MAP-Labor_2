package com.online_shop.MAP_Labor_2_Spring.models;

import com.online_shop.MAP_Labor_2_Spring.interfaces.PaymentStrategy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Card extends PaymentStrategy {

    private String name;
    private String card_number;
    private Integer cvv;
    private Integer expiration_month;
    private Integer expiration_year;

}
