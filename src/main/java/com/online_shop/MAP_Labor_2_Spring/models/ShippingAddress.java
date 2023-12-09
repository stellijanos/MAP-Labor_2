package com.online_shop.MAP_Labor_2_Spring.models;

import org.online_shop.interfaces.UserObserver;

import java.util.ArrayList;
import java.util.List;

public class ShippingAddress implements UserObserver {
    private Integer id;
    private User user;
    private String name;
    private String phone;
    private String address;
    private String city;
    private String zipCode;
    private final List<Order> orders = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", orders=" + orders +
                '}';
    }

    @Override
    public void update(String firstname, String lastname, String email, String password, ShoppingCart shoppingCart) {
        this.user.setFirstname(firstname);
        this.user.setLastname(lastname);
        this.user.setEmail(email);
        this.user.setPassword(password);
        this.user.set$shoppingCart(shoppingCart);
    }
}
