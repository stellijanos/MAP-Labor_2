package org.online_shop.repositories;

import org.online_shop.models.Database;
import org.online_shop.models.Product;
import org.online_shop.models.ProductSpec;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends Database {

    private final CategoryRepository categoryRepository;
    private final ProductSpecRepository productSpecRepository;

    public ProductRepository() {
        this.categoryRepository = new CategoryRepository();
        this.productSpecRepository = new ProductSpecRepository();
    }

    public boolean create(Product product) {
        String sql = "INSERT INTO products(name, price, category_id, description, image_link, stock) " +
                "VALUES(?, ?, ?, ?, ?, ?);";
        try (PreparedStatement prepStmt = conn().prepareStatement(sql)) {
            prepStmt.setString(1, product.getName());
            prepStmt.setFloat(2, product.getPrice());
            prepStmt.setInt(3, product.getCategory().getId());
            prepStmt.setString(4, product.getDescription());
            prepStmt.setString(5, product.getImageLink());
            prepStmt.setInt(6, product.getStock());

            return prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public Product read(Integer id) {
        String sql = "SELECT * FROM products WHERE id = ?;";

        try (PreparedStatement prepStmt = conn().prepareStatement(sql)) {
            prepStmt.setInt(1, id);
            ResultSet resultSet = prepStmt.executeQuery();
            Product product = new Product();
            if (resultSet.next()) {
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getFloat("price"));
                product.setDescription(resultSet.getString("description"));
                product.setImageLink(resultSet.getString("image_link"));
                product.setStock(resultSet.getInt("stock"));
                product.setCategory(categoryRepository.read(resultSet.getInt("category_id")));

                List<ProductSpec> productSpecs = productSpecRepository.readAll(product);
                for (ProductSpec spec : productSpecs)
                    product.setProductSpecs(spec);

            }
            return product;
        } catch (SQLException e) {
            return new Product();
        }
    }

    public List<Product> readAll() {
        String sql = "SELECT * FROM products;";
        try (Statement stmt = conn().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);

            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getFloat("price"));
                product.setDescription(resultSet.getString("description"));
                product.setImageLink(resultSet.getString("image_link"));
                product.setStock(resultSet.getInt("stock"));

                product.setCategory(categoryRepository.read(resultSet.getInt("category_id")));
                List<ProductSpec> productSpecs = productSpecRepository.readAll(product);
                for (ProductSpec spec : productSpecs)
                    product.setProductSpecs(spec);

                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public boolean update(Product updatedProduct) {
        String sql = "UPDATE products SET name=? , price=?, description=?, category_id=?, image_link=? WHERE id=?;";
        try (PreparedStatement prepStmt = conn().prepareStatement(sql)) {
            prepStmt.setString(1, updatedProduct.getName());
            prepStmt.setFloat(2, updatedProduct.getPrice());
            prepStmt.setString(3, updatedProduct.getDescription());
            prepStmt.setInt(4, updatedProduct.getCategory().getId());
            prepStmt.setString(5, updatedProduct.getImageLink());
            prepStmt.setInt(6, updatedProduct.getId());

            return prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM products WHERE id=?;";
        try (PreparedStatement prepStmt = conn().prepareStatement(sql)) {
            prepStmt.setInt(1, id);

            return prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteAll() {
        String sql = "DELETE FROM products;";
        try (Statement stmt = conn().createStatement()) {
            return stmt.execute(sql);
        } catch (SQLException e) {
            return false;
        }
    }
}
