package model;

import app.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkRepository
{
    public List<Work> getWorkList()
    {
        try
        {
            Connection connection = DBConnection.getConnection();
            String sql = """
                    SELECT *
                    FROM works
                    WHERE store_id = ? AND staff_id = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Main.store.getStore_id());
            preparedStatement.setInt(2, Main.staff.getStaff_id());
            ResultSet rs = preparedStatement.executeQuery();

            List<Work> workList = new ArrayList<Work>();
            while (rs.next())
            {
                int work_id = rs.getInt("work_id");
                int store_id = rs.getInt("store_id");
                int staff_id = rs.getInt("staff_id");
                Timestamp log_date = rs.getTimestamp("log_date");
                String cause = rs.getString("cause");

                Work newWork = new Work(work_id, store_id, staff_id, log_date, cause);
                workList.add(newWork);
            }

            return workList;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
}
