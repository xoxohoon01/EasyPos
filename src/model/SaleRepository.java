package model;

import app.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleRepository
{
    public List<Sale> getTodaySaleList()
    {
        String sql = """
                SELECT * 
                FROM sales 
                WHERE store_id = ? AND TRUNC(sale_date) = TRUNC(SYSDATE)
                ORDER BY sale_id ASC
                """;
        List<Sale> saleList = new ArrayList<Sale>();
        try
        {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Main.store.getStore_id());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                int sale_id = rs.getInt("sale_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Timestamp sale_date = rs.getTimestamp("sale_date");
                Sale newSale = new Sale(sale_id, Main.store.getStore_id(), product_id, quantity, sale_date);
                saleList.add(newSale);
            }

            return saleList;
        }
        catch (SQLException e)
        {

        }

        return null;
    }

    public List<Sale> getSaleListByDate(int year, int month, int day)
    {
        String sql = """
                SELECT * 
                FROM sales 
                WHERE store_id = ? AND TRUNC(sale_date) = TO_DATE(?, 'YYYY-MM-DD')
                ORDER BY sale_id ASC
                """;
        List<Sale> saleList = new ArrayList<Sale>();

        try
        {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Main.store.getStore_id());
            preparedStatement.setString(2, String.format("%d-%02d-%02d", year, month, day));
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                int sale_id = rs.getInt("sale_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Timestamp sale_date = rs.getTimestamp("sale_date");
                Sale newSale = new Sale(sale_id, Main.store.getStore_id(), product_id, quantity, sale_date);
                saleList.add(newSale);
            }

            return saleList;
        }
        catch (SQLException e)
        {

        }
        return null;
    }

    public List<Sale> getSaleList()
    {
        String sql = """
                SELECT *
                FROM sales
                WHERE store_id = ?
                ORDER BY sale_id ASC
                """;
        List<Sale> saleList = new ArrayList<Sale>();
        try
        {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Main.store.getStore_id());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                int sale_id = rs.getInt("sale_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Timestamp sale_date = rs.getTimestamp("sale_date");
                Sale newSale = new Sale(sale_id, Main.store.getStore_id(), product_id, quantity, sale_date);
                saleList.add(newSale);
            }

            return saleList;
        }
        catch (SQLException e)
        {

        }

        return null;
    }

    public void recordSale(int product_id, int quantity, int payAmount)
    {
        String sqlInsertSale = """
            INSERT INTO sales (store_id, product_id, quantity, sale_date)
            VALUES (?, ?, ?, SYSTIMESTAMP)
            """;

        try
        {
            Connection connection = DBConnection.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(sqlInsertSale);
            psInsert.setInt(1, Main.store.getStore_id());
            psInsert.setInt(2, product_id);
            psInsert.setInt(3, quantity);
            psInsert.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
