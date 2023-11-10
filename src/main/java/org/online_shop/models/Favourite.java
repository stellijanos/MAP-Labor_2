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

    @Override
    public String toString() {
        return "Favourite{" +
                "_userId=" + _userId +
                ", _productId=" + _productId +
                '}';
    }
}
