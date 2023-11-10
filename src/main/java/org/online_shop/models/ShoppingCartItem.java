package org.online_shop.models;

public class ShoppingCartItem {
    private int _shoppingCartId;
    private int _productId;

    private int quantity;



    public ShoppingCartItem(int _shoppingCartId, int _userId) {
        this._shoppingCartId = _shoppingCartId;
        this._productId = _userId;
    }

    public int get_shoppingCartId() {
        return _shoppingCartId;
    }

    public void set_shoppingCartId(int _shoppingCartId) {
        this._shoppingCartId = _shoppingCartId;
    }

    public int get_productId() {
        return _productId;
    }

    public void set_productId(int _productId) {
        this._productId = _productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "_shoppingCartId=" + _shoppingCartId +
                ", _userId=" + _productId +
                '}';
    }
}
