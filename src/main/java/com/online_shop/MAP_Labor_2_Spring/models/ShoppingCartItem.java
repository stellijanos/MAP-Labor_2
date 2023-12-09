package com.online_shop.MAP_Labor_2_Spring.models;

public class ShoppingCartItem {
    private ShoppingCart shoppingCart;
    private Product product;
    private Integer quantity;

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "_shoppingCart=" + shoppingCart +
                ", product=" + product +
                ", _quantity=" + quantity +
                '}';
    }
}
