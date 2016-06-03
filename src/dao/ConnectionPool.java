package dao;

import java.sql.*;

public class ConnectionPool {

    private static ConnectionPool instance = new ConnectionPool();
    private Connection connection;

    private ConnectionPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException,
            ClassNotFoundException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/itemsdatabase", "root", "kempas91");
            return connection;

        }
        return connection;
    }
}