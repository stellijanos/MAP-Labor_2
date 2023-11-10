package org.online_shop.models;

public class ShoppingCart {
    private Integer _id;
    private Integer _userId;


    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer get_userId() {
        return _userId;
    }

    public void set_userId(Integer _userId) {
        this._userId = _userId;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "_id=" + _id +
                ", _userId=" + _userId +
                '}';
    }
}
