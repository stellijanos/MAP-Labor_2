package com.online_shop.MAP_Labor_2_Spring.controllers;

import com.online_shop.MAP_Labor_2_Spring.models.ShippingAddress;
import com.online_shop.MAP_Labor_2_Spring.services.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/shipping_addresses")
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    @Autowired
    public ShippingAddressController(ShippingAddressService shippingAddressService) {
        this.shippingAddressService = shippingAddressService;
    }

    @GetMapping
    public ResponseEntity<Iterable<ShippingAddress>> getAllShippingAddresses() {
        return shippingAddressService.getAllShippingAddresses();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShippingAddress> update(@RequestBody ShippingAddress shippingAddress) {
        return shippingAddressService.update(shippingAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return shippingAddressService.delete(id);
    }
}
