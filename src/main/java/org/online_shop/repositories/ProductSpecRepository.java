package org.online_shop.repositories;

import org.online_shop.models.DatabaseInMemory;
import org.online_shop.models.ProductSpec;

import java.util.List;
import java.util.stream.Collectors;

public class ProductSpecRepository extends DatabaseInMemory {
    public boolean create(ProductSpec productSpec) {
        return _productSpecs.add(productSpec);
    }

    public List<ProductSpec> readAll(Integer productId) {
        return _productSpecs.stream()
                .filter(spec -> spec.get_productId().equals(productId))
                .collect(Collectors.toList());
    }

    public boolean update(ProductSpec productSpec) {
        return _productSpecs.stream()
                .filter(spec -> spec.get_id().equals(productSpec.get_id()))
                .findFirst()
                .map(spec -> {
                    spec.set_specName(productSpec.get_specName());
                    spec.set_specValue(productSpec.get_specValue());
                    return true;
                }).orElse(false);
    }

    public boolean delete(Integer specId) {
         return _productSpecs.removeIf(spec -> spec.get_id().equals(specId));
    }

    public boolean deleteAll() {
        _productSpecs.clear();
        return true;
    }
}
