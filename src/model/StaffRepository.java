package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRepository
{
    public boolean checkStaffID(int staff_id)
    {
        String sql = "SELECT staff_id FROM staffs WHERE staff_id = ?";
        try
        {
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, staff_id);
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

    public Staff login(int staff_id, String password)
    {
        String sql = "SELECT staff_id, staff_name, password FROM staffs WHERE staff_id=? AND password=?";
        try
        {
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, staff_id);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                return new Staff(rs.getInt("staff_id"), rs.getString("staff_name"));
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
