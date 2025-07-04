package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository
{
    public List<Product> getProductList()
    {
        List<Product> productList = new ArrayList<Product>();

        try
        {
            String sql = """
                    SELECT * FROM products ORDER BY product_id
                    """;
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                int product_id = rs.getInt(1);
                String name = rs.getString(2);
                String category = rs.getString(3);
                int price = rs.getInt(4);
                String isAdult = rs.getString(5);
                String company = rs.getString(6);
                Product targetProduct = new Product(product_id, name, category, price, isAdult, company);
                productList.add(targetProduct);
            }
            return productList;
        }
        catch (SQLException e)
        {

        }

        // DB 데이터를 List로 저장하는 데에 실패했을 경우
        return null;
    }
    public Product getProductByID(int product_id)
    {
        try
        {
            String sql = """
                    SELECT * FROM products WHERE product_id = ?
                    """;
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product_id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                String name = rs.getString(2);
                String category = rs.getString(3);
                int price = rs.getInt(4);
                String isAdult = rs.getString(5);
                String company = rs.getString(6);
                return new Product(product_id, name, category, price, isAdult, company);
            }
        }
        catch (SQLException e)
        {

        }

        return null;
    }
}
