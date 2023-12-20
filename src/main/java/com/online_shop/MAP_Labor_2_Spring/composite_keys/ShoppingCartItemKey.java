package com.online_shop.MAP_Labor_2_Spring.composite_keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class ShoppingCartItemKey implements Serializable {
    @Column(name = "shopping_cart_id")
    Long shoppingCartId;

    @Column(name = "product_id")
    Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCartItemKey that)) return false;
        return Objects.equals(getShoppingCartId(), that.getShoppingCartId()) &&
                Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShoppingCartId(), getProductId());
    }
}

