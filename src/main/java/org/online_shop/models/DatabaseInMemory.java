package org.online_shop.models;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInMemory {
    protected List<Category> categories = new ArrayList<>();
    protected List<Favourite> favourites = new ArrayList<>();
    protected List<Order> orders = new ArrayList<>();
    protected List<OrderItem> orderItems = new ArrayList<>();
    protected List<Product> products = new ArrayList<>();
    protected List<ProductSpec> productSpecs = new ArrayList<>();
    protected List<ShippingAddress> shippingAddresses = new ArrayList<>();
    protected List<ShoppingCart> shoppingCarts = new ArrayList<>();
    protected List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();
    protected List<User> users = new ArrayList<>();
}
