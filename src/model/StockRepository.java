package model;

import app.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockRepository
{
    public List<Stock> getStockList()
    {
        String sql = """
                SELECT * FROM stocks WHERE store_id = ?
                """;
        try
        {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Main.store.getStore_id());
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

    public Stock getStock(int targetProduct_id)
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
}
