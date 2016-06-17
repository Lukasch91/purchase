package com.lukas.dao;

import com.lukas.db.ConnectionFactory;

import java.sql.Connection;

/**
 * Created by Lukas on 6/13/2016.
 */
public class DAOFactory {

    public static ItemDAO getItemDAO() {
        return new ItemDAO(ConnectionFactory.getConnection());
    }

    public static PurchaseDAO getPurchaseDAO() {
        return new PurchaseDAO(ConnectionFactory.getConnection());
    }

    public static PurchaseDAO getPurchaseDAO(Connection connection) {
        return new PurchaseDAO(connection);
    }

    public static RecordsDAO getRecordsDAO() {
        return new RecordsDAO(ConnectionFactory.getConnection());
    }

    public static RecordsDAO getRecordsDAO(Connection connection) {
        return new RecordsDAO(connection);
    }
}
