package org.online_shop.models;

import java.sql.Timestamp;

public class Order {
    private int _id;
    private int _userId;
    private int _shippingAddressId;
    private String _paymentMethod;
    private Timestamp _date;
    private String _status;
    private float _shippingFee;

    public Order(int _id, int _userId, int _shippingAddressId, String _paymentMethod, Timestamp _date, String _status, float _shippingFee) {
        this._id = _id;
        this._userId = _userId;
        this._shippingAddressId = _shippingAddressId;
        this._paymentMethod = _paymentMethod;
        this._date = _date;
        this._status = _status;
        this._shippingFee = _shippingFee;
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

    public int get_shippingAddressId() {
        return _shippingAddressId;
    }

    public void set_shippingAddressId(int _shippingAddressId) {
        this._shippingAddressId = _shippingAddressId;
    }

    public String get_paymentMethod() {
        return _paymentMethod;
    }

    public void set_paymentMethod(String _paymentMethod) {
        this._paymentMethod = _paymentMethod;
    }

    public Timestamp get_date() {
        return _date;
    }

    public void set_date(Timestamp _date) {
        this._date = _date;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public float get_shippingFee() {
        return _shippingFee;
    }

    public void set_shippingFee(float _shippingFee) {
        this._shippingFee = _shippingFee;
    }

    @Override
    public String toString() {
        return "Order{" +
                "_id=" + _id +
                ", _userId=" + _userId +
                ", _shippingAddressId=" + _shippingAddressId +
                ", _paymentMethod='" + _paymentMethod + '\'' +
                ", _date=" + _date +
                ", status='" + _status + '\'' +
                ", _shippingFee=" + _shippingFee +
                '}';
    }
}
