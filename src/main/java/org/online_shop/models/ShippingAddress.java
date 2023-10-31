package org.online_shop.models;

public class ShippingAddress {
    private int _id;
    private int _userId;
    private String name;
    private String _phone;
    private String _address;
    private String _city;
    private String _zipCode;

    public ShippingAddress(int _id, int _userId, String name, String _phone, String _address, String _city, String _zipCode) {
        this._id = _id;
        this._userId = _userId;
        this.name = name;
        this._phone = _phone;
        this._address = _address;
        this._city = _city;
        this._zipCode = _zipCode;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_zipCode() {
        return _zipCode;
    }

    public void set_zipCode(String _zipCode) {
        this._zipCode = _zipCode;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "_id=" + _id +
                ", _userId=" + _userId +
                ", name='" + name + '\'' +
                ", _phone='" + _phone + '\'' +
                ", _address='" + _address + '\'' +
                ", _city='" + _city + '\'' +
                ", _zipCode='" + _zipCode + '\'' +
                '}';
    }
}
