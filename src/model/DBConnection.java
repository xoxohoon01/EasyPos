package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XE"; // 오라클 XE 예제 URL
    private static final String USER = "c##pos";      // DB 사용자 계정
    private static final String PASSWORD = "1234"; // DB 비밀번호

    static
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
        }
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException("Oracle JDBC Driver 로드 실패", e);
        }

    }

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
