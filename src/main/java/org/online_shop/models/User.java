package org.online_shop.models;
import java.sql.Time;
import java.sql.Timestamp;

public class User {
    private int _id;
    private String _firstname;
    private String _lastname;
    private String _email;
    private Timestamp _createdAt;

    public User(int _id, String _firstname, String _lastname, String _email, Timestamp _createdAt) {
        this._id = _id;
        this._firstname = _firstname;
        this._lastname = _lastname;
        this._email = _email;
        this._createdAt = _createdAt;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    public Timestamp get_createdAt() {
        return _createdAt;
    }

    public void set_createdAt(Timestamp _createdAt) {
        this._createdAt = _createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", _firstname='" + _firstname + '\'' +
                ", _lastname='" + _lastname + '\'' +
                ", _email='" + _email + '\'' +
                ", _createdAt=" + _createdAt +
                '}';
    }
}
