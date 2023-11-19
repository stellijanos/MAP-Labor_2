package org.online_shop.repositories;

import org.online_shop.models.Category;
import org.online_shop.models.Database;
import org.online_shop.models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends Database {
    public boolean create(Product product) {
        String sql = "INSERT INTO products(name, price, category_id, description, image_link, stock) " +
                "VALUES(?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement prepStmt = conn().prepareStatement(sql);
            prepStmt.setString(1, product.get_name());
            prepStmt.setFloat(2, product.get_price());
            prepStmt.setInt(3, product.get_category().get_id());
            prepStmt.setString(4, product.get_description());
            prepStmt.setString(5, product.get_imageLink());
            prepStmt.setInt(6, product.get_stock());

            return prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public Product read(Integer id) {
        String sql = "SELECT p.id, p.name, p.price, p.category_id, p.description, p.image_link, p.stock,\n" +
                "c.name AS category_name\n" +
                "FROM products p " +
                "JOIN categories c on c.id = p.category_id;";
        try {
            PreparedStatement prepStmt = conn().prepareStatement(sql);
            prepStmt.setInt(1, id);
            ResultSet resultSet = prepStmt.executeQuery();
            Product product = new Product();
            while (resultSet.next()) {
                product.set_id(resultSet.getInt("id"));
                product.set_name(resultSet.getString("name"));
                product.set_price(resultSet.getFloat("price"));
                product.set_description(resultSet.getString("description"));
                product.set_imageLink(resultSet.getString("image_link"));
                product.set_stock(resultSet.getInt("stock"));

                Category category = new Category();
                category.set_id(resultSet.getInt("category_id"));
                category.set_name(resultSet.getString("category_name"));

                product.set_category(category);

            }
            return product;
        } catch (SQLException e) {
            return new Product();
        }
    }

    public List<Product> readAll() {
        String sql = "SELECT p.id, p.name, p.price, p.category_id, p.description, p.image_link, p.stock,\n" +
                "c.name AS category_name\n" +
                "FROM products p " +
                "JOIN categories c on c.id = p.category_id;";
        try {
            Statement stmt = conn().createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            List<Product> products = new ArrayList<>();

            Product product = new Product();
            while (resultSet.next()) {
                product.set_id(resultSet.getInt("id"));
                product.set_name(resultSet.getString("name"));
                product.set_price(resultSet.getFloat("price"));
                product.set_description(resultSet.getString("description"));
                product.set_imageLink(resultSet.getString("image_link"));
                product.set_stock(resultSet.getInt("stock"));

                Category category = new Category();
                category.set_id(resultSet.getInt("category_id"));
                category.set_name(resultSet.getString("category_name"));

                product.set_category(category);

                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public boolean update(Product updatedProduct) {
        String sql = "UPDATE products SET name=? , price=?, description=?, category_id=?, image_link=? WHERE id=?;";
        try {
            PreparedStatement prepStmt = conn().prepareStatement(sql);
            prepStmt.setString(1, updatedProduct.get_name());
            prepStmt.setFloat(2, updatedProduct.get_price());
            prepStmt.setString(3, updatedProduct.get_description());
            prepStmt.setInt(4, updatedProduct.get_category().get_id());
            prepStmt.setString(5, updatedProduct.get_imageLink());
            prepStmt.setInt(6, updatedProduct.get_id());

            return prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM products WHERE id=?;";
        try {
            PreparedStatement prepStmt = conn().prepareStatement(sql);
            prepStmt.setInt(1, id);

            return prepStmt.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteAll() {
        String sql = "DELETE FROM products;";
        try {
            Statement stmt = conn().createStatement();
            return stmt.execute(sql);
        } catch (SQLException e) {
            return false;
        }
    }
}
