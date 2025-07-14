package model;

import app.Main;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StockRepository
{
    public List<Stock> getStockList(int targetStore_id)
    {
        String sql = """
                SELECT * FROM stocks WHERE store_id = ?
                ORDER BY product_id ASC
                """;
        try
        {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, targetStore_id);
            ResultSet rs = preparedStatement.executeQuery();

            List<Stock> stockList = new ArrayList<Stock>();
            while (rs.next())
            {
                int stock_id = rs.getInt(1);
                int store_id = rs.getInt(2);
                int product_id = rs.getInt(3);
                int quantity = rs.getInt(4);
                Timestamp registered_date = rs.getTimestamp(5);
                Timestamp expiration_date = rs.getTimestamp(6);
                Stock targetStock = new Stock(stock_id, store_id, product_id, quantity, registered_date, expiration_date);
                stockList.add(targetStock);
            }
            return stockList;
        }
        catch (SQLException e)
        {
        }

        return null;
    }

    public Stock getStockByProductId(int targetProduct_id)
    {
        String sql = """
                SELECT * FROM stocks WHERE store_id = ? AND product_id = ?
                """;

        try
        {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Main.store.getStore_id());
            preparedStatement.setInt(2, targetProduct_id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                int stock_id = rs.getInt(1);
                int store_id = rs.getInt(2);
                int product_id = rs.getInt(3);
                int quantity = rs.getInt(4);
                Timestamp registered_date = rs.getTimestamp(5);
                Timestamp expiration_date = rs.getTimestamp(6);
                Stock targetStock = new Stock(stock_id, store_id, product_id, quantity, registered_date, expiration_date);
                return targetStock;
            }
        }
        catch (SQLException e)
        {

        }
        return null;
    }

    public Stock getStockByStockId(int targetStock_id)
    {
        String sql = """
                SELECT * FROM stocks WHERE store_id = ? AND stock_id = ?
                """;

        try
        {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Main.store.getStore_id());
            preparedStatement.setInt(2, targetStock_id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                int stock_id = rs.getInt(1);
                int store_id = rs.getInt(2);
                int product_id = rs.getInt(3);
                int quantity = rs.getInt(4);
                Timestamp registered_date = rs.getTimestamp(5);
                Timestamp expiration_date = rs.getTimestamp(6);
                Stock targetStock = new Stock(stock_id, store_id, product_id, quantity, registered_date, expiration_date);
                return targetStock;
            }
        }
        catch (SQLException e)
        {

        }
        return null;
    }

    public void registerStockByDeliveryId(int delivery_id) {
        String sqlSelect = """
            SELECT product_id, quantity
            FROM delivery
            WHERE delivery_id = ? AND store_id = ?
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
             PreparedStatement psSelect = conn.prepareStatement(sqlSelect)) {

            psSelect.setInt(1, delivery_id);
            psSelect.setInt(2, Main.store.getStore_id());
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
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
                    psDelete.setInt(1, delivery_id);
                    psDelete.executeUpdate();
                }

                System.out.printf("[등록 완료 - delivery_id] 상품ID: %d, 수량: %d%n", productId, quantity);
            } else {
                System.out.println("[등록 실패] 해당 delivery_id의 입고 내역이 없습니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerStockByProductId(int targetProduct_id, int targetQuantity) {

        String sqlInsert = """
                INSERT INTO stocks (store_id, product_id, quantity, registered_date, expiration_date)
                VALUES (?, ?, ?, ?, ?)
                """;

        try
        {
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
            preparedStatement.setInt(1, Main.store.getStore_id());
            preparedStatement.setInt(2, targetProduct_id);
            preparedStatement.setInt(3, targetQuantity);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
            preparedStatement.setTimestamp(5, null);

            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean decreaseStock(int stock_id, int quantity)
    {

        String sqlUpdateStock = """
            UPDATE stocks
            SET quantity = quantity - ?
            WHERE store_id = ? AND stock_id = ? AND quantity >= ?
            """;

        try
        {
            Connection connection = DBConnection.getConnection();

            PreparedStatement psUpdate = connection.prepareStatement(sqlUpdateStock);
            psUpdate.setInt(1, quantity);
            psUpdate.setInt(2, Main.store.getStore_id());
            psUpdate.setInt(3, stock_id);
            psUpdate.setInt(4, quantity);

            int updatedRows = psUpdate.executeUpdate();
            return updatedRows > 0;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
