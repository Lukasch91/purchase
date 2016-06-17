package com.lukas.dao;

import com.lukas.db.DBUtil;
import com.lukas.model.Record;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RecordsDAO {

    private Connection connection;

    RecordsDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertRecords(Record record) {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO itemsdatabase.records (Id, itemName, itemPrice, numberOfItems, sum, purchaseId)" +
                    " VALUES (null, ?, ?, ?, ?, ?);";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, record.getItem().getItemName());
            stmt.setDouble(2, record.getItem().getItemPrice().intValue());
            stmt.setInt(3, record.getNumberOfItems());
            stmt.setDouble(4, record.sumOfItem().intValue());
            stmt.setInt(5, getMaxId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeStmt(stmt);
        }
    }

    private int getMaxId() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "select max(id) from itemsdatabase.purchase";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("max(id)");
            } else {
                throw new RuntimeException("Could not found max id for purchase");
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
