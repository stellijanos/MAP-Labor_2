package com.online_shop.MAP_Labor_2_Spring.models.repositories;

import org.online_shop.models.Database;
import org.online_shop.models.Product;
import org.online_shop.models.ProductSpec;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecRepository extends Database {
    public boolean create(ProductSpec productSpec) {

        String sql = "INSERT INTO product_specs(product_id, spec_name, spec_value) VALUES(?, ?, ?);";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, productSpec.getProduct().getId());
            stmt.setString(2, productSpec.getSpecName());
            stmt.setString(3, productSpec.getSpecValue());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<ProductSpec> readAll(Product product) {
        String sql = "SELECT * FROM product_specs WHERE product_id = ?";

        try {
            List<ProductSpec> productSpecs = new ArrayList<>();

            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, product.getId());
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                ProductSpec productSpec = new ProductSpec();
                productSpec.setProduct(product);
                productSpec.setId(resultSet.getInt("id"));
                productSpec.setSpecName(resultSet.getString("spec_name"));
                productSpec.setSpecValue(resultSet.getString("spec_value"));

                productSpecs.add(productSpec);
            }
            return productSpecs;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public boolean update(ProductSpec productSpec) {

        String sql = "UPDATE product_specs SET spec_name = ?, spec_value = ? WHERE id = ?";

        try {

            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setString(1, productSpec.getSpecName());
            stmt.setString(2, productSpec.getSpecValue());
            stmt.setInt(3, productSpec.getId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Integer specId) {
        String sql = "DELETE FROM product_specs WHERE id = ?";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, specId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
