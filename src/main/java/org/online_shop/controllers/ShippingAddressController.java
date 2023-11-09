package org.online_shop.controllers;

import org.online_shop.repositories.ShippingAddressRepository;

public class ShippingAddressController {

    private ShippingAddressRepository _shippingAddressRepository;

    public ShippingAddressController(ShippingAddressRepository shippingAddressRepository) {
        this._shippingAddressRepository = shippingAddressRepository;
    }
}
