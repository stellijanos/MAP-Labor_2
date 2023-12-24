package com.online_shop.MAP_Labor_2_Spring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online_shop.MAP_Labor_2_Spring.composite_keys.ShoppingCartItemKey;
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

    @EmbeddedId
    private ShoppingCartItemKey id;

    @Column(name = "quantity")
    private Integer quantity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
