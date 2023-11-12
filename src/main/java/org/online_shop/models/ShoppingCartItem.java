package org.online_shop.models;

public class ShoppingCartItem {
    private ShoppingCart _shoppingCart;
    private Product _product;
    private Integer _quantity;

    public ShoppingCart get_shoppingCart() {
        return _shoppingCart;
    }

    public void set_shoppingCart(ShoppingCart _shoppingCart) {
        this._shoppingCart = _shoppingCart;
    }

    public Product get_product() {
        return _product;
    }

    public void set_product(Product product) {
        this._product = product;
    }

    public Integer get_quantity() {
        return _quantity;
    }

    public void set_quantity(Integer _quantity) {
        this._quantity = _quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "_shoppingCart=" + _shoppingCart +
                ", product=" + _product +
                ", _quantity=" + _quantity +
                '}';
    }
}
