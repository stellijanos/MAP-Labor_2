package org.online_shop.models;

public class Favourite {

    private int _userId;
    private int _productId;

    public Favourite(int userId, int productId) {
        _userId = userId;
        _productId = productId;
    }

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    public int get_productId() {
        return _productId;
    }

    public void set_productId(int _productId) {
        this._productId = _productId;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "_userId=" + _userId +
                ", _productId=" + _productId +
                '}';
    }
}
