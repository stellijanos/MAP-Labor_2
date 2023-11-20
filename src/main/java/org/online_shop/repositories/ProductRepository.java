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
        try (PreparedStatement stmt = conn().prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setFloat(2, product.getPrice());
            stmt.setInt(3, product.getCategory().getId());
            stmt.setString(4, product.getDescription());
            stmt.setString(5, product.getImageLink());
            stmt.setInt(6, product.getStock());

            return stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public Product read(Integer id) {
        String sql = "SELECT * FROM products WHERE id = ?;";

        try (PreparedStatement stmt = conn().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
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
        try (PreparedStatement stmt = conn().prepareStatement(sql)) {
            stmt.setString(1, updatedProduct.getName());
            stmt.setFloat(2, updatedProduct.getPrice());
            stmt.setString(3, updatedProduct.getDescription());
            stmt.setInt(4, updatedProduct.getCategory().getId());
            stmt.setString(5, updatedProduct.getImageLink());
            stmt.setInt(6, updatedProduct.getId());

            return stmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM products WHERE id=?;";
        try (PreparedStatement stmt = conn().prepareStatement(sql)) {
            stmt.setInt(1, id);

            return stmt.execute();
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
