package org.online_shop.repositories;

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
            stmt.setInt(1, productSpec.get_product().get_id());
            stmt.setString(2, productSpec.get_specName());
            stmt.setString(3, productSpec.get_specValue());
            return stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public List<ProductSpec> readAll(Product product) {
        String sql = "SELECT * FROM product_specs WHERE product_id = ?";

        try {
            List<ProductSpec> productSpecs = new ArrayList<>();

            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, product.get_id());
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                ProductSpec productSpec = new ProductSpec();
                productSpec.set_product(product);
                productSpec.set_id(resultSet.getInt("id"));
                productSpec.set_specName(resultSet.getString("spec_name"));
                productSpec.set_specValue(resultSet.getString("spec_value"));

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
            stmt.setString(1, productSpec.get_specName());
            stmt.setString(2, productSpec.get_specValue());
            stmt.setInt(3, productSpec.get_id());

            return stmt.execute();

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Integer specId) {
        String sql = "DELETE FROM product_specs WHERE id = ?";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, specId);
            return stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }
}
