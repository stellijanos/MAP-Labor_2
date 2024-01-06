package com.online_shop.MAP_Labor_2_Spring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payments")
public abstract class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Float amount;

    @JsonIgnore
    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Order order;


    public void pay(){}

    @Override
    public String toString() {
        return "Payment{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", order=" + order +
                '}';
    }
}
