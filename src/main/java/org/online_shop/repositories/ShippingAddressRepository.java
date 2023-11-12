package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.ShippingAddress;
import org.online_shop.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class ShippingAddressRepository extends DatabaseInMemory {
    public boolean create(ShippingAddress shippingAddress) {
        return _shippingAddresses.add(shippingAddress);
    }

    public ShippingAddress read(Integer id, User user) {
        return _shippingAddresses.stream()
                .filter(address -> address.get_id().equals(id) && address.get_user().equals(user))
                .findFirst()
                .orElse(new ShippingAddress());
    }

    public List<ShippingAddress> readAll(User user) {
        return _shippingAddresses.stream().filter(address -> address.get_user().equals(user)).collect(Collectors.toList());
    }

    public boolean update(ShippingAddress shippingAddress) {
        return _shippingAddresses.stream()
                .filter(address -> address.get_id().equals(shippingAddress.get_id()) && address.get_user().equals(shippingAddress.get_user()))
                .findFirst()
                .map(address -> {
                    address.set_name(shippingAddress.get_name());
                    address.set_phone(shippingAddress.get_phone());
                    address.set_address(shippingAddress.get_address());
                    address.set_city(shippingAddress.get_city());
                    address.set_zipCode(shippingAddress.get_zipCode());
                    return true;
                }).orElse(false);
    }

    public boolean delete(ShippingAddress shippingAddress) {
        return _shippingAddresses.remove(shippingAddress);
    }

    public boolean deleteAll(Integer userId) {
        return _shippingAddresses.removeIf(address -> address.get_user().equals(userId));
    }
}
