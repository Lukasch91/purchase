package com.lukas.dao;

import com.lukas.db.DBUtil;
import com.lukas.model.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PurchaseDAO {

    private Connection connection;

    PurchaseDAO(Connection connection) {
        this.connection = connection;
    }

    public void addPurchaseToDatabase(Purchase purchase) {
        PreparedStatement stmt = null;
        try {

            String sql = "INSERT INTO itemsdatabase.purchase (id, date, time, type, sum) VALUES (NULL, ?, ?, 'P', ?);";
            stmt = connection.prepareStatement(sql);

            Date dt = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");

            String date = sdf.format(dt);
            String time = stf.format(dt);
            stmt.setString(1, date);
            stmt.setString(2, time);
            stmt.setDouble(3, purchase.sumPrices());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeStmt(stmt);
        }
    }


    public void addWithdrawalToDatabase(Double withdrawal) {
        PreparedStatement stmt = null;
        try {

            String sql = "INSERT INTO itemsdatabase.purchase (id, date, time, type, sum) VALUES (NULL, ?, ?, 'W', ?);";
            stmt = connection.prepareStatement(sql);

            Date dt = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");

            String date = sdf.format(dt);
            String time = stf.format(dt);
            stmt.setString(1, date);
            stmt.setString(2, time);
            stmt.setDouble(3, -withdrawal);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeStmt(stmt);
        }
    }

    public Double getCurrentBalance() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT SUM(sum) AS Total FROM itemsdatabase.purchase;";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            if (rs.next())
                return rs.getDouble("total");
            else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeRS(rs);
            DBUtil.closeStmt(stmt);
        }
    }

    public Double getBalanceByDate(String date) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT sum(sum) FROM itemsdatabase.purchase where date <= ? ";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, date);
            rs = stmt.executeQuery();

            if (rs.next())
                return rs.getDouble("sum(sum)");
            else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeRS(rs);
            DBUtil.closeStmt(stmt);
        }
    }
}
