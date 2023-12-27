package com.online_shop.MAP_Labor_2_Spring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online_shop.MAP_Labor_2_Spring.composite_keys.OrderItemKey;
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

    @EmbeddedId
    private OrderItemKey id;
    private Integer quantity;
    private Float price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("orderId")
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

}
