package org.online_shop.models;

public class Favourite {

    private Integer _userId;
    private Integer _productId;

    public Favourite(Integer userId, Integer productId) {
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
