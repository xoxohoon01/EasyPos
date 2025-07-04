package model;

import app.Main;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository
{
    public void registerOrder(List<Order> orderList)
    {
        for (int i = 0; i < orderList.size(); i++)
        {
            String sql = "INSERT INTO orders(store_id, product_id, quantity, order_date) VALUES(?, ?, ?, ?)";

            try
            {
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, Main.store.getStore_id());
                preparedStatement.setInt(2, orderList.get(i).getProduct_id());
                preparedStatement.setInt(3, orderList.get(i).getQuantity());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

                preparedStatement.execute();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
