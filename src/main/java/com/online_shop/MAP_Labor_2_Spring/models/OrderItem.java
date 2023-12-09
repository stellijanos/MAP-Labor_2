package com.online_shop.MAP_Labor_2_Spring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "order_items")
public class OrderItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;
    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private Float price;
    @Column(name = "quantity")
    private Integer quantity;
}
