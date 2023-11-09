package org.online_shop.models;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInMemory {
    protected List<Category> _categories = new ArrayList<>();
    protected List<Favourite> _favourites = new ArrayList<>();
    protected List<Order> _orders = new ArrayList<>();
    protected List<OrderItem> _orderItems = new ArrayList<>();
    protected List<Product> _products = new ArrayList<>();
    protected List<ProductSpec> _productSpecs = new ArrayList<>();
    protected List<ShippingAddress> _shippingAddresses = new ArrayList<>();
    protected List<ShoppingCart> _shoppingCarts = new ArrayList<>();
    protected List<ShoppingCartItem> _shoppingCartItems = new ArrayList<>();
    protected List<User> _users = new ArrayList<>();
}
