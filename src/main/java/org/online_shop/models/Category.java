package org.online_shop.models;

public class Category {

    private int _id;
    private String _name;

    public Category(int _id, String _name) {
        this._id = _id;
        this._name = _name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "_id=" + _id +
                ", name='" + _name + '\'' +
                '}';
    }
}
