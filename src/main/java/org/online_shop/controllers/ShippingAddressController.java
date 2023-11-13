package org.online_shop.controllers;

import org.online_shop.models.ShippingAddress;
import org.online_shop.models.User;
import org.online_shop.repositories.ShippingAddressRepository;

import java.util.List;

public class ShippingAddressController {

    private final ShippingAddressRepository _shippingAddressRepository;

    public ShippingAddressController(ShippingAddressRepository shippingAddressRepository) {
        this._shippingAddressRepository = shippingAddressRepository;
    }

    public List<ShippingAddress> getAll(User user) {
        return _shippingAddressRepository.readAll(user);
    }
}
