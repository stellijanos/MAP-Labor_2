//package com.online_shop.MAP_Labor_2_Spring.controllers;
//
//import com.online_shop.MAP_Labor_2_Spring.models.ShippingAddress;
//import com.online_shop.MAP_Labor_2_Spring.repositories.ShippingAddressRepository;
//import org.mindrot.jbcrypt.BCrypt;
//import com.online_shop.MAP_Labor_2_Spring.enums.Response;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//public class ShippingAddressController {
//
//    private ShippingAddressRepository shippingAddressRepository;
//
//    public @ResponseBody void createAddress(@RequestBody ShippingAddress shippingAddress) {
//        shippingAddressRepository.save(shippingAddress);
//    }
//
//    @GetMapping("/{id}")
//    public ShippingAddress getAddress(@PathVariable Integer id) {
//        return shippingAddressRepository.findById(id).orElse(null);
//    }
//
//
//    @GetMapping
//    public @ResponseBody List<ShippingAddress> getAllAddresses() {
//        return (List<ShippingAddress>) shippingAddressRepository.findAll();
//    }
//
//    @PutMapping("/{id}")
//    public @ResponseBody void update(@RequestBody ShippingAddress shippingAddress) {
//        shippingAddressRepository.save(shippingAddress);
//    }
//
//    @DeleteMapping("/{id}")
//    public @ResponseBody void delete(@PathVariable Integer id) {
//        shippingAddressRepository.deleteById(id);
//    }
//
//    @DeleteMapping
//    public void deleteAll() {
//         shippingAddressRepository.deleteAll();
//    }
//}
