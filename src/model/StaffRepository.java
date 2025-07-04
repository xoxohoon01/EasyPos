package model;

import app.Main;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                String sqlUpdateWorksTable = """
                        INSERT INTO works(store_id, staff_id, log_date, cause) VALUES(?, ?, ?, ?)
                        """;
                try
                {
                    PreparedStatement worksTable = conn.prepareStatement(sqlUpdateWorksTable);

                    worksTable.setInt(1, Main.store.getStore_id());
                    worksTable.setInt(2, rs.getInt("staff_id"));
                    worksTable.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                    worksTable.setString(4, "Enter");

                    worksTable.execute();
                    return new Staff(rs.getInt("staff_id"), rs.getString("staff_name"));
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                return null;
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

    public void logout()
    {
        String sqlUpdateWorksTable = """
                        INSERT INTO works(store_id, staff_id, log_date, cause) VALUES(?, ?, ?, ?)
                        """;
        try
        {
            Connection conn = DBConnection.getConnection();
            PreparedStatement worksTable = conn.prepareStatement(sqlUpdateWorksTable);

            worksTable.setInt(1, Main.store.getStore_id());
            worksTable.setInt(2, Main.staff.getStaff_id());
            worksTable.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
            worksTable.setString(4, "Leave");

            worksTable.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
