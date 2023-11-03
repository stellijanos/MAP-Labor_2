package org.online_shop.models;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInMemory {
    public List<Category> categories = new ArrayList<>();
    public List<Favourite> favourites = new ArrayList<>();
    public List<Order> orders = new ArrayList<>();
    public List<OrderItem> orderItems = new ArrayList<>();
    public List<Product> products = new ArrayList<>();
    public List<ProductSpec> productSpecs = new ArrayList<>();
    public List<ShippingAddress> shippingAddresses = new ArrayList<>();
    public List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();
    public List<User> users = new ArrayList<>();
}
