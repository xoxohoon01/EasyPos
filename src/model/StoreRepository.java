package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreRepository
{
    public boolean checkStoreID(int storeID)
    {
        String sql = "SELECT store_id FROM stores WHERE store_id = ?";
        try
        {
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, storeID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false; // 로그인 실패
    }

    public Store login(int store_id, String password)
    {
        String sql = "SELECT store_id, store_name, password FROM stores WHERE store_id = ? AND password = ?";
        try
        {
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, store_id);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                return new Store(rs.getInt("store_id"), rs.getString("store_name"));
            }
            else
            {
                return null;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null; // 로그인 실패
    }
}
