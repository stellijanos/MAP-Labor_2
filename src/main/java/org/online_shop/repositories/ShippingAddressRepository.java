package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.ShippingAddress;

import java.util.List;
import java.util.stream.Collectors;

public class ShippingAddressRepository extends DatabaseInMemory {
    public boolean create(ShippingAddress shippingAddress) {
        return _shippingAddresses.add(shippingAddress);
    }

    public ShippingAddress read(Integer id, Integer userId) {
        for (ShippingAddress address : _shippingAddresses)
            if (address.get_id().equals(id) && address.get_userId().equals(userId))
                return address;
        return new ShippingAddress();
    }

    public List<ShippingAddress> readAll(Integer userId) {
        return _shippingAddresses.stream().filter(address -> address.get_userId().equals(userId)).collect(Collectors.toList());
    }

    public boolean update(ShippingAddress shippingAddress) {
        for (ShippingAddress address : _shippingAddresses)
            if (address.get_id().equals(shippingAddress.get_id())) {
                address.set_name(shippingAddress.get_name());
                address.set_phone(shippingAddress.get_phone());
                address.set_address(shippingAddress.get_address());
                address.set_city(shippingAddress.get_city());
                address.set_zipCode(shippingAddress.get_zipCode());
                return true;
            }
        return false;
    }

    public boolean delete(ShippingAddress shippingAddress) {
        return _shippingAddresses.remove(shippingAddress);
    }

    public boolean deleteAll(Integer userId) {
        return _shippingAddresses.removeIf(address -> address.get_userId().equals(userId));
    }
}
