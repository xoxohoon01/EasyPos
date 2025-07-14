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

    public List<Order> getOrderList(int targetStore_id)
    {
        OrderRepository orderRepository = new OrderRepository();

        String sql = """
                SELECT * FROM orders WHERE store_id = ?
                ORDER BY store_id, product_id ASC
                """;

        try
        {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, targetStore_id);
            ResultSet rs = preparedStatement.executeQuery();

            List<Order> orderList = new ArrayList<Order>();
            while (rs.next())
            {
                int order_id = rs.getInt("order_id");
                int store_id = rs.getInt("store_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Timestamp order_date = rs.getTimestamp("order_date");
                Order targetOrder = new Order(order_id, store_id, product_id, quantity);
                orderList.add(targetOrder);
            }
            return orderList;
        }
        catch (SQLException e)
        {

        }

        return null;
    }
}
