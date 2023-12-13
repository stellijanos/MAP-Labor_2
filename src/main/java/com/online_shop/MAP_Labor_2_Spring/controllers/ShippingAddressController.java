package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.ShippingAddress;
import com.online_shop.MAP_Labor_2_Spring.repositories.ShippingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/shipping_addresses")
public class ShippingAddressController {

    private final ShippingAddressRepository shippingAddressRepository;

    @Autowired
    public ShippingAddressController(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
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

}
