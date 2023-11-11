package org.online_shop.models;

public class ShoppingCartItem {
    private Integer _shoppingCartId;
    private Integer _productId;
    private Integer _quantity;


    public Integer get_shoppingCartId() {
        return _shoppingCartId;
    }

    public void set_shoppingCartId(Integer _shoppingCartId) {
        this._shoppingCartId = _shoppingCartId;
    }

    public Integer get_productId() {
        return _productId;
    }

    public void set_productId(Integer _productId) {
        this._productId = _productId;
    }

    public Integer get_quantity() {
        return _quantity;
    }

    public boolean set_quantity(Integer quantity) {
        this._quantity = quantity;
        return true;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "_shoppingCartId=" + _shoppingCartId +
                ", _userId=" + _productId +
                '}';
    }
}
