//package com.online_shop.MAP_Labor_2_Spring.composite_keys;
//
//import com.online_shop.MAP_Labor_2_Spring.models.Order;
//import com.online_shop.MAP_Labor_2_Spring.models.Product;
//import jakarta.persistence.Embeddable;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.io.Serializable;
//
//@Setter
//@Getter
//@Embeddable
//public class OrderItemKey implements Serializable {
//    @ManyToOne
//    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private Order order;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private Product product;
//}
//
