package org.online_shop.models;

public class ShoppingCartItem {
    private int _shoppingCartId;
    private int _userId;

    public ShoppingCartItem(int _shoppingCartId, int _userId) {
        this._shoppingCartId = _shoppingCartId;
        this._userId = _userId;
    }

    public int get_shoppingCartId() {
        return _shoppingCartId;
    }

    public void set_shoppingCartId(int _shoppingCartId) {
        this._shoppingCartId = _shoppingCartId;
    }

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "_shoppingCartId=" + _shoppingCartId +
                ", _userId=" + _userId +
                '}';
    }
}
