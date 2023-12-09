package com.online_shop.MAP_Labor_2_Spring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "shopping_cart_items")
public class ShoppingCartItem {

    @Id
    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ShoppingCart shoppingCart;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;
    @Column(name = "quantity")
    private Integer quantity;
}
