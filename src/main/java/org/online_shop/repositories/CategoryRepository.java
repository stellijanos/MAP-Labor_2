package org.online_shop.repositories;

import org.online_shop.models.Category;
import org.online_shop.models.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository extends Database {

    public boolean create(Category category) {
        String sql = "INSERT INTO categories(name) VALUES(?);";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setString(1, category.getName());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public Category read(Integer id) {
        String sql = "SELECT * FROM categories where id = ?";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            Category category = new Category();
            if (resultSet.next()) {
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
            }
            return category;
        } catch (SQLException e) {
            return new Category();
        }
    }

    public List<Category> readAll() {
        String sql = "SELECT * FROM categories;";

        try {
            Statement stmt = conn().createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            List<Category> categories = new ArrayList<>();

            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public boolean update(Category updatedCategory) {
        String sql = "UPDATE categories SET name = ? WHERE id = ?;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setString(1, updatedCategory.getName());
            stmt.setInt(2, updatedCategory.getId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM categories WHERE id = ?;";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteAll() {
        String sql = "DELETE FROM categories;";

        try {
            Statement stmt = conn().createStatement();
            int rows = stmt.executeUpdate(sql);
            return rows > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
