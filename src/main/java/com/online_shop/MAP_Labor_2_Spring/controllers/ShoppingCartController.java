//package com.online_shop.MAP_Labor_2_Spring.controllers;
//
//import com.online_shop.MAP_Labor_2_Spring.enums.Response;
//import com.online_shop.MAP_Labor_2_Spring.models.ShoppingCart;
//import com.online_shop.MAP_Labor_2_Spring.repositories.Env;
//import com.online_shop.MAP_Labor_2_Spring.repositories.ShoppingCartRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Objects;
//
//
//@RestController
//@RequestMapping("/api/shopping_cart")
//public class ShoppingCartController {
//
//    @Autowired
//    private ShoppingCartRepository shoppingCartRepository;
//
//    @PostMapping("/create")
//    public @ResponseBody Response create(
//            @RequestParam(name = "token") String token,
//            @RequestBody ShoppingCart shoppingCart) {
//
//        if (!Objects.equals(Env.load().get("API_TOKEN"), token))
//            return Response.INVALID_TOKEN;
//
//        ShoppingCart current = shoppingCartRepository.findById(shoppingCart.getId()).orElse(new ShoppingCart());
//        if (current.getUser().getEmail() != null) {
//            return Response.USER_ALREADY_EXISTS;
//        }
//        shoppingCartRepository.save(shoppingCart);
//        return Response.USER_CREATE_SUCCESSFUL;
//    }
//
//    @GetMapping("/{id}")
//    public @ResponseBody ShoppingCart read(
//            @RequestParam(name = "token") String token,
//            @PathVariable Integer id) {
//        return (!Objects.equals(Env.load().get("API_TOKEN"), token)) ? new ShoppingCart() :
//                shoppingCartRepository.findById(id).orElse(new ShoppingCart());
//    }
//
//    @DeleteMapping("/{email}")
//    public @ResponseBody Response delete(
//            @RequestParam String token,
//            @RequestParam Long id
//    ) {
//        if (!Objects.equals(Env.load().get("API_TOKEN"), token))
//            return Response.INVALID_TOKEN;
//        shoppingCartRepository.deleteById(id);
//        return Response.USER_DELETE_SUCCESSFUL;
//    }
//}
