package org.online_shop.views;

import org.online_shop.models.ShippingAddress;

import java.util.List;

public class ShippingAddressView {
    public void view(ShippingAddress shippingAddress) {
        System.out.println(shippingAddress);
    }

    public void viewAll(List<ShippingAddress> shippingAddresses) {
        for (ShippingAddress shippingAddress:shippingAddresses) {
            System.out.println(shippingAddress);
        }
    }
}
