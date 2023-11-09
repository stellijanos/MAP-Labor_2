package org.online_shop.models;

public class Category {

    private int _id;
    private String _name;


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                '}';
    }
}
