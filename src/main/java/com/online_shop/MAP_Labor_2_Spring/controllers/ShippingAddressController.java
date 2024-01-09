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

    /**
     * Endpoint to retrieve all shipping addresses.
     * Handles GET requests to retrieve all shipping addresses.
     *
     * @return ResponseEntity containing a list of all shipping addresses
     */
    @GetMapping
    public ResponseEntity<Iterable<ShippingAddress>> getAllShippingAddresses() {
        return shippingAddressService.getAllShippingAddresses();
    }

    /**
     * Endpoint to retrieve a specific shipping address by ID.
     * Handles GET requests to retrieve a shipping address by its ID.
     *
     * @param id ID of the shipping address to retrieve
     * @return ResponseEntity containing the shipping address information
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShippingAddress> getShippingAddress(@PathVariable Long id) {
        return shippingAddressService.getShippingAddress(id);
    }

    /**
     * Endpoint to update a shipping address.
     * Handles PUT requests to update a shipping address by its ID and updated shipping address details.
     *
     * @param shippingAddress ShippingAddress object containing updated shipping address information
     * @return ResponseEntity containing the updated shipping address information
     */
    @PutMapping("/{id}")
    public ResponseEntity<ShippingAddress> update(@RequestBody ShippingAddress shippingAddress) {
        return shippingAddressService.update(shippingAddress);
    }

    /**
     * Endpoint to delete a shipping address by its ID.
     * Handles DELETE requests to delete a shipping address by its ID.
     *
     * @param id ID of the shipping address to delete
     * @return ResponseEntity indicating the deletion status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return shippingAddressService.delete(id);
    }
}
