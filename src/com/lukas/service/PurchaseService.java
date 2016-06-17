package com.lukas.service;

import com.lukas.dao.DAOFactory;
import com.lukas.dao.PurchaseDAO;
import com.lukas.dao.RecordsDAO;
import com.lukas.db.ConnectionFactory;
import com.lukas.db.DBUtil;
import com.lukas.model.Purchase;
import com.lukas.model.Record;

import java.sql.Connection;
import java.sql.SQLException;


public class PurchaseService {

    public void addPurchasesAndItemsToDatabase(Purchase purchase) {
        Connection conn = ConnectionFactory.getConnection(false);
        try {
            PurchaseDAO purchaseDAO = DAOFactory.getPurchaseDAO(conn);
            purchaseDAO.addPurchaseToDatabase(purchase);
            RecordsDAO recordsDAO = DAOFactory.getRecordsDAO(conn);

            for (Record record : purchase.getRecords()) {
                if (record != null) {
                    recordsDAO.insertRecords(record);
                }
            }
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ignored) {
            }
        } finally {
            DBUtil.closeConn(conn);
        }
    }


    public void addWithdrawal(Double sum) {
        DAOFactory.getPurchaseDAO().addWithdrawalToDatabase(sum);
    }

    public Double getCurrentBalance() {
        return DAOFactory.getPurchaseDAO().getCurrentBalance();
    }
    public Double getBalanceByDate(String date){
        return DAOFactory.getPurchaseDAO().getBalanceByDate(date);
    }


}

