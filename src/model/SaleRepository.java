package model;

import app.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleRepository
{
    public List<Sale> getTodaySaleList()
    {
        String sql = "SELECT * FROM sales WHERE store_id = ? AND TRUNC(sale_date) = TRUNC(SYSDATE)";
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
                Sale newSale = new Sale(sale_id, product_id, quantity, sale_date);
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
        String sql = "SELECT * FROM sales WHERE store_id = ?";
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
                Sale newSale = new Sale(sale_id, product_id, quantity, sale_date);
                saleList.add(newSale);
            }

            return saleList;
        }
        catch (SQLException e)
        {

        }

        return null;
    }
}
