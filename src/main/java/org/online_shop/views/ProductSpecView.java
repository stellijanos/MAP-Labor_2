package org.online_shop.views;

import org.online_shop.models.ProductSpec;

import java.util.List;

public class ProductSpecView {
    public void view(ProductSpec productSpec) {
        System.out.println(productSpec);
    }

    public void viewAll(List<ProductSpec> productSpecs) {
        for (ProductSpec productSpec:productSpecs) {
            System.out.println(productSpec);
        }
    }
}
