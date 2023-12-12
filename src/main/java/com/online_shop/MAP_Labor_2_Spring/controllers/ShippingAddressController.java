package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.ShippingAddress;
import com.online_shop.MAP_Labor_2_Spring.repositories.ShippingAddressRepository;
import com.online_shop.MAP_Labor_2_Spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/shipping_addresses")
public class ShippingAddressController {

    private final ShippingAddressRepository shippingAddressRepository;
    private final UserRepository userRepository;

    @Autowired
    public ShippingAddressController(ShippingAddressRepository shippingAddressRepository, UserRepository userRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public @ResponseBody ShippingAddress createAddress(@RequestBody ShippingAddress shippingAddress) {
        return shippingAddressRepository.save(shippingAddress);
    }

    @GetMapping("/user/{user_id}")
    public @ResponseBody List<ShippingAddress> getAllAddressByUserId(@PathVariable Long user_id) {
        return shippingAddressRepository.findAllByUserId(user_id);
    }

    @GetMapping
    public @ResponseBody List<ShippingAddress> getAll() {
        return (List<ShippingAddress>) shippingAddressRepository.findAll();
    }

    @PutMapping("/{id}")
    public @ResponseBody ShippingAddress update(@RequestBody ShippingAddress shippingAddress) {
        return shippingAddressRepository.save(shippingAddress);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody void delete(@PathVariable Long id) {
        shippingAddressRepository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
         shippingAddressRepository.deleteAll();
    }
}
