package org.online_shop.controllers;

import org.online_shop.enums.Response;
import org.online_shop.models.ShippingAddress;
import org.online_shop.models.User;
import org.online_shop.repositories.ShippingAddressRepository;

import java.util.List;

public class ShippingAddressController {

    private final ShippingAddressRepository _shippingAddressRepository;

    public ShippingAddressController(ShippingAddressRepository shippingAddressRepository) {
        this._shippingAddressRepository = shippingAddressRepository;
    }

    public Response addAddress(String name, String phone, String address, String city, String zipcode, User user) {

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setUser(user);
        shippingAddress.setName(name);
        shippingAddress.setPhone(phone);
        shippingAddress.setAddress(address);
        shippingAddress.setCity(city);
        shippingAddress.setZipCode(zipcode);

        return _shippingAddressRepository.create(shippingAddress)
                ? Response.SHIPPING_ADDRESS_CREATED_SUCCESSFULLY : Response.SOMETHING_WENT_WRONG;
    }

    public List<ShippingAddress> getAll(User user) {
        return _shippingAddressRepository.readAll(user);
    }
}
