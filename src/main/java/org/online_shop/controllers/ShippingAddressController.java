package org.online_shop.controllers;

import org.mindrot.jbcrypt.BCrypt;
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

    public ShippingAddress getAddress(Integer id, User user) {
        return _shippingAddressRepository.read(id, user);
    }

    public List<ShippingAddress> getAll(User user) {
        return _shippingAddressRepository.readAll(user);
    }

    public Response modify(Integer id, String name, String phone, String address, String city, String zipcode, User user) {

        ShippingAddress current = _shippingAddressRepository.read(id, user);
        if (current.getName().isEmpty())
            return Response.SHIPPING_ADDRESS_NOT_FOUND;

        ShippingAddress updated = new ShippingAddress();
        updated.setId(id);
        updated.setName(name.isEmpty() ? current.getName() : name);
        updated.setPhone(phone.isEmpty() ? current.getPhone() : phone);
        updated.setAddress(address.isEmpty() ? current.getAddress() : address);
        updated.setCity(city.isEmpty() ? current.getCity() : city);
        updated.setZipCode(zipcode.isEmpty() ? current.getZipCode() : zipcode);
        updated.setUser(user);

        return _shippingAddressRepository.update(updated)
                ? Response.SHIPPING_ADDRESS_UPDATED_SUCCESSFULLY: Response.SOMETHING_WENT_WRONG;
    }

    public Response remove(Integer id, User user) {
        ShippingAddress current = _shippingAddressRepository.read(id, user);
        if (current.getName().isEmpty())
            return Response.SHIPPING_ADDRESS_NOT_FOUND;
        return _shippingAddressRepository.delete(current)
                ? Response.SHIPPING_ADDRESS_DELETED_SUCCESSFULLY : Response.SOMETHING_WENT_WRONG;
    }

    public Response removeAll(String password, User user) {
        return (!BCrypt.checkpw(password, user.getPassword()))
                ? Response.INCORRECT_PASSWORD : _shippingAddressRepository.deleteAll(user)
                ? Response.ALL_SHIPPING_ADDRESSES_DELETED_SUCCESSFULLY : Response.SOMETHING_WENT_WRONG;
    }

}
