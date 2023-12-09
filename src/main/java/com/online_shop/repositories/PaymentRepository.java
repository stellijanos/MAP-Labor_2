package org.online_shop.repositories;

import org.online_shop.interfaces.PaymentStrategy;
import org.online_shop.models.Card;
import org.online_shop.models.Cash;
import org.online_shop.models.Database;

import java.awt.image.RescaleOp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRepository extends Database {

    public PaymentStrategy read(Integer id) {
        String sql = "SELECT * FROM payments WHERE id = ?";

        try {
            PreparedStatement stmt = conn().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            PaymentStrategy paymentStrategy = null;

            if (resultSet.next()) {
                String type = resultSet.getString("type");

                if (type.equals("card")) {
                    String cardName = resultSet.getString("cardname");
                    String number = resultSet.getString("number");
                    String cvv = resultSet.getString("cvv");
                    paymentStrategy = new Card(cardName, number, cvv);

                } else if (type.equals("cash")) {
                    paymentStrategy = new Cash();
                }
            }
            return paymentStrategy;
        } catch (SQLException e) {
            return null;
        }
    }
}
