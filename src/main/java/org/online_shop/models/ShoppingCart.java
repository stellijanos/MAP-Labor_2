package org.online_shop.models;

public class ShoppingCart {
    private int _id;
    private int _userId;


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
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
