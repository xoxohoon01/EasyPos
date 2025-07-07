package model;

import app.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeliveryRepository
{
    public void simulateIncomingDeliveries() {
        String sqlSelectOrders = """
        SELECT order_id, store_id, product_id, quantity
        FROM orders
        WHERE store_id = ?
        """;

        String sqlInsertDelivery = """
        INSERT INTO delivery (store_id, product_id, quantity, delivery_date)
        VALUES (?, ?, ?, SYSTIMESTAMP)
        """;

        String sqlDeleteOrder = "DELETE FROM orders WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psSelect = conn.prepareStatement(sqlSelectOrders)) {

            psSelect.setInt(1, Main.store.getStore_id());
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int storeId = rs.getInt("store_id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");

                try (PreparedStatement psInsert = conn.prepareStatement(sqlInsertDelivery)) {
                    psInsert.setInt(1, storeId);
                    psInsert.setInt(2, productId);
                    psInsert.setInt(3, quantity);
                    psInsert.executeUpdate();
                }

                try (PreparedStatement psDelete = conn.prepareStatement(sqlDeleteOrder)) {
                    psDelete.setInt(1, orderId);
                    psDelete.executeUpdate();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerDeliveries() {
        String sqlSelectDelivery = """
        SELECT delivery_id, product_id, quantity
        FROM delivery
        WHERE store_id = ?
        """;

        String sqlMergeStock = """
        MERGE INTO stocks s
        USING (SELECT ? AS product_id, ? AS quantity FROM dual) d
        ON (s.store_id = ? AND s.product_id = d.product_id)
        WHEN MATCHED THEN
            UPDATE SET s.quantity = s.quantity + d.quantity
        WHEN NOT MATCHED THEN
            INSERT (store_id, product_id, quantity, registered_date)
            VALUES (?, ?, ?, SYSTIMESTAMP)
        """;

        String sqlDeleteDelivery = "DELETE FROM delivery WHERE delivery_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psSelect = conn.prepareStatement(sqlSelectDelivery)) {

            psSelect.setInt(1, Main.store.getStore_id());
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                int deliveryId = rs.getInt("delivery_id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");

                try (PreparedStatement psMerge = conn.prepareStatement(sqlMergeStock)) {
                    psMerge.setInt(1, productId);
                    psMerge.setInt(2, quantity);
                    psMerge.setInt(3, Main.store.getStore_id());
                    psMerge.setInt(4, Main.store.getStore_id());
                    psMerge.setInt(5, productId);
                    psMerge.setInt(6, quantity);

                    psMerge.executeUpdate();
                }

                try (PreparedStatement psDelete = conn.prepareStatement(sqlDeleteDelivery)) {
                    psDelete.setInt(1, deliveryId);
                    psDelete.executeUpdate();
                }

                System.out.printf("[입고 등록 완료] 상품ID: %d, 수량: %d%n", productId, quantity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
