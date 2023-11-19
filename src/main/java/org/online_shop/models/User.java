package org.online_shop.models;

import org.online_shop.interfaces.UserObserver;
import org.online_shop.interfaces.Subject;

import java.util.ArrayList;
import java.util.List;


public class User implements Subject {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String createdAt;
    private ShoppingCart shoppingCart;
    private final List<UserObserver> observers;
    private final List<Product> favourites;

    public User() {
        this.observers = new ArrayList<>();
        this.favourites = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<UserObserver> getObservers() {
        return observers;
    }

    public List<Product> getFavourites() {
        return favourites;
    }

    public void setFavourites(Product product) {
        favourites.add(product);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", shoppingCart=" + shoppingCart +
                '}';
    }

    @Override
    public void add(UserObserver observer) {
        observers.add(observer);
    }

    @Override
    public void remove(UserObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (UserObserver observer : observers) {
            observer.update(firstname, lastname, email, password, shoppingCart);
        }
    }
}
