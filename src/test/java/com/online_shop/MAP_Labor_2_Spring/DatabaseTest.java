package com.online_shop.MAP_Labor_2_Spring;

import com.online_shop.MAP_Labor_2_Spring.models.Database;
import org.junit.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseTest {

    @Test
    public void test_connection() {
        Database database = new Database();
        Connection connection = database.conn();

        assertNotNull(connection);

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT 1");

            assertTrue(resultSet.next());
            assertEquals(1, resultSet.getInt(1));

            resultSet.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            fail("Database connection failed: " + e.getMessage());
        }
    }
}
