package org.online_shop.models;

public class ShippingAddress {
    private Integer _id;
    private Integer _userId;
    private String _name;
    private String _phone;
    private String _address;
    private String _city;
    private String _zipCode;

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

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
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
                ", name='" + _name + '\'' +
                ", _phone='" + _phone + '\'' +
                ", _address='" + _address + '\'' +
                ", _city='" + _city + '\'' +
                ", _zipCode='" + _zipCode + '\'' +
                '}';
    }
}
