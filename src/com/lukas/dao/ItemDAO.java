package com.lukas.dao;

import java.sql.*;

import com.lukas.db.DBUtil;

import com.lukas.model.item.*;

public class ItemDAO  {

    private Connection connection;

    ItemDAO(Connection connection) {
        this.connection = connection;
    }

    public Item loadItem(Long itemCode) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            System.out.println("Creating statement...");

            String sql = "select itemCode, itemName, ItemPrice, typeOfDiscount, discountAmount " +
                    "from itemsdatabase.items left outer join itemsdatabase.discount" +
                    "on  discount.discountId = itemstable.discountId where itemCode = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, itemCode);
            rs = stmt.executeQuery(sql);

            if (rs.next()) {

                String itemName = rs.getString("itemName");
                Double itemPrice = rs.getDouble("itemPrice");
                String typeOfDiscount = rs.getString("typeOfDiscount");
                Double discountAmount = rs.getDouble("discountAmount");

                Discount discount = null;

                if (typeOfDiscount == null) {
                    discount = new NoDiscount();
                }
                if ("Z".equals(typeOfDiscount)) {
                    discount = new AmountDiscount(discountAmount);
                } else if ("F".equals(typeOfDiscount)) {
                    discount = new PercentageDiscount(discountAmount);
                }
                return new Item(itemName, itemPrice, discount);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeStmt(stmt);
            DBUtil.closeRS(rs);
        }
        return null;

    }

    public int countItems() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            System.out.println("Creating statement...");

            String sql = "SELECT COUNT(*) as result from itemsdatabase.items";
            stmt = connection.prepareStatement(sql);

            rs = stmt.executeQuery(sql);

            if (rs.next())
                return rs.getInt("result");
            else {
                throw new RuntimeException("Could not count items");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeStmt(stmt);
            DBUtil.closeRS(rs);
        }

    }

    public Item selectRandomItem(int randomNumber) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "select itemCode, itemName, ItemPrice, typeOfDiscount, discountAmount \n" +
                    "from items left outer join discount \n" +
                    "on  discount.discountId = items.discountId limit ?, 1;";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, randomNumber - 1);
            rs = stmt.executeQuery();

            if (rs.next()) {

                String itemName = rs.getString("itemName");
                Double itemPrice = rs.getDouble("itemPrice");
                String typeOfDiscount = rs.getString("typeOfDiscount");
                Double discountAmount = rs.getDouble("discountAmount");

                Discount discount = null;

                if (typeOfDiscount == null) {
                    discount = new NoDiscount();
                }
                if ("Z".equals(typeOfDiscount)) {
                    discount = new AmountDiscount(discountAmount);
                } else if ("F".equals(typeOfDiscount)) {
                    discount = new PercentageDiscount(discountAmount);
                }
                return new Item(itemName, itemPrice, discount);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeStmt(stmt);
            DBUtil.closeRS(rs);
        }
    }
}


