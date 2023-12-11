package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.enums.Response;
import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCart;
import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCartItem;
import com.online_shop.MAP_Labor_2_Spring.repositories.ShoppingCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shopping_cart_items")
public class ShoppingCartItemController {
    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @GetMapping("/{id}")
    public List<ShoppingCartItem> getAll(@PathVariable Integer id) {
        return (List<ShoppingCartItem>) this.shoppingCartItemRepository.findAllByShoppingCartId(id);
    }

}
