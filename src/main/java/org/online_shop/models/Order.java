package org.online_shop.models;

import java.sql.Timestamp;

public class Order {
    private Integer _id;
    private Integer _userId;
    private Integer _shippingAddressId;
    private String _paymentMethod;
    private Timestamp _date;
    private String _status;
    private Float _shippingFee;


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

    public Integer get_shippingAddressId() {
        return _shippingAddressId;
    }

    public void set_shippingAddressId(Integer _shippingAddressId) {
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

    public Float get_shippingFee() {
        return _shippingFee;
    }

    public void set_shippingFee(Float _shippingFee) {
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
