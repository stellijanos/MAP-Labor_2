package com.online_shop.MAP_Labor_2_Spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {
    private String name;
    private String type;
    private Float aomunt;


    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public void pay() {
    }

    @Override
    public String toString() {
        return "Payment{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", aomunt=" + aomunt +
                ", order=" + order +
                '}';
    }
}
