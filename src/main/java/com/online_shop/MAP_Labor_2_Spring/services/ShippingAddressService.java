package com.online_shop.MAP_Labor_2_Spring.services;

import com.online_shop.MAP_Labor_2_Spring.models.ShippingAddress;
import com.online_shop.MAP_Labor_2_Spring.repositories.ShippingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    @Autowired
    public ShippingAddressService(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
    }

    public ResponseEntity<Iterable<ShippingAddress>> getAllShippingAddresses() {
        return ResponseEntity.ok(shippingAddressRepository.findAll());
    }

    public ResponseEntity<ShippingAddress> update(ShippingAddress shippingAddress) {

        Optional<ShippingAddress> optionalShippingAddress = shippingAddressRepository.findById(shippingAddress.getId());

        if (optionalShippingAddress.isEmpty())
            return ResponseEntity.notFound().build();

        ShippingAddress existingShippingAddress = optionalShippingAddress.get();

        existingShippingAddress.setName(shippingAddress.getName());
        existingShippingAddress.setPhone(shippingAddress.getPhone());
        existingShippingAddress.setAddress(shippingAddress.getAddress());
        existingShippingAddress.setCity(shippingAddress.getCity());
        existingShippingAddress.setZipcode(shippingAddress.getZipcode());

        return ResponseEntity.ok(shippingAddressRepository.save(shippingAddress));
    }

    public ResponseEntity<String> delete(Long id) {
        if (!shippingAddressRepository.existsById(id))
            return ResponseEntity.notFound().build();
        shippingAddressRepository.deleteById(id);
        return ResponseEntity.ok().body("Shipping Address deleted successfully!");
    }
}
