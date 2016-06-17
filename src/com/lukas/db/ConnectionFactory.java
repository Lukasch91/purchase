package com.lukas.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory instance = new ConnectionFactory();

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/itemsdatabase";
    private static final String USER = "root";
    private static final String PASSWORD = "kempas91";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";


    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection(boolean autoCommit) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection(true);
    }

    public static Connection getConnection(boolean autoCommit) {
        return instance.createConnection(autoCommit);
    }
}