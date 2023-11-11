package org.online_shop.models;

import org.online_shop.controllers.CustomControllerTools;


public class User {
    private Integer _id;
    private String _firstname;
    private String _lastname;
    private String _email;
    private String _password;
    private final String _createdAt;


    public User() {
        this._createdAt = CustomControllerTools.getCurrentDateTIme();
    }


    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String get_firstname() {
        return _firstname;
    }

    public void set_firstname(String _firstname) {
        this._firstname = _firstname;
    }

    public String get_lastname() {
        return _lastname;
    }

    public void set_lastname(String _lastname) {
        this._lastname = _lastname;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_createdAt() {
        return _createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", _firstname='" + _firstname + '\'' +
                ", _lastname='" + _lastname + '\'' +
                ", _email='" + _email + '\'' +
                ", _createdAt=" + _createdAt +
                ", _password=" + _password +
                "}\n";
    }
}
