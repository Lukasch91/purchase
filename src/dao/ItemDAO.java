package dao;

import java.sql.*;

import cashRegister.CashRegister;
import model.Entry;
import model.Purchase;
import model.item.*;

public class ItemDAO implements DataWorker {


    public Item loadItem(Long itemCode) {
        PreparedStatement stmt = null;
        try {
            System.out.println("Creating statement...");

            String sql = "select itemCode, itemName, ItemPrice, typeOfDiscount, discountAmount " +
                    "from itemsdatabase.itemstable left outer join itemsdatabase.discounttable " +
                    "on  discounttable.discountId = itemstable.discountId where itemCode = ?";
            Connection conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, itemCode);
            ResultSet rs = stmt.executeQuery(sql);

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

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
            }
        }
        return null;

    }

    public int countItems() {
        PreparedStatement stmt = null;
        try {

            System.out.println("Creating statement...");

            String sql = "SELECT COUNT(*) as result from itemsdatabase.itemstable";
            Connection conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) return rs.getInt("result");


            rs.close();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
            }
        }
        return 0;
    }

    public Item selectRandomItem(int randomNumber) {
        PreparedStatement stmt = null;
        try {
            String sql = "select itemCode, itemName, ItemPrice, typeOfDiscount, discountAmount \n" +
                    "from itemstable left outer join discounttable \n" +
                    "on  discounttable.discountId = itemstable.discountId limit ?, 1;";
            Connection conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, randomNumber - 1);
            ResultSet rs = stmt.executeQuery();

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

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
            }
        }
        return null;

    }

    public static void addPurchaseToDatabase(Purchase purchase) {
        PreparedStatement stmt = null;
        try {

            String sql = "INSERT INTO itemsdatabase.purchase (id, date, type, sum) VALUES (NULL, ?, 'P', ?);";
            Connection conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            try {
                java.util.Date dt = new java.util.Date();

                java.text.SimpleDateFormat sdf =
                        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String currentTime = sdf.format(dt);
                stmt.setString(1, currentTime);
                stmt.setDouble(2, purchase.sumPrices());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
            }

        }
    }


    public static void addToDatabase(Purchase purchase) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            for (Entry entry : purchase.getItems())
                if (entry != null) {
                    String sql = "select max(id) from itemsdatabase.purchase";

                    conn = ConnectionPool.getInstance().getConnection();
                    stmt = conn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        int purchaseId = rs.getInt("max(id)");


                        String sql2 = "INSERT INTO itemsdatabase.Item (Id, itemName, itemPrice, numberOfItems, sum, purchaseId)" +
                                " VALUES (null, ?, ?, ?, ?, ?);";
                        stmt = conn.prepareStatement(sql2);
                        stmt.setString(1, entry.getItem().getItemName());
                        stmt.setDouble(2, entry.getItem().getItemPrice().intValue());
                        stmt.setInt(3, entry.getNumberOfItems());
                        stmt.setDouble(4, entry.sumOfItem().intValue());
                        stmt.setInt(5, purchaseId);
                        stmt.executeUpdate();
                        conn.commit();
                    }
                }


        } catch (Exception e) {
            assert conn != null;
            conn.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
                assert conn != null;
                conn.rollback();
            } catch (SQLException ignored) {
                assert conn != null;
                conn.rollback();
            }

        }
    }

    public static void addWithdrawalToDatabase() throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {

            String sql = "INSERT INTO itemsdatabase.purchase (id, date, type, sum) VALUES (NULL, ?, 'W', ?);";
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            conn.commit();
            try {

                Double withdrawal = CashRegister.withdrawals();
                java.util.Date dt = new java.util.Date();

                java.text.SimpleDateFormat sdf =
                        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String currentTime = sdf.format(dt);
                stmt.setString(1, currentTime);
                stmt.setDouble(2, -withdrawal);
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            assert conn != null;
            conn.rollback();
            throw new RuntimeException(e);
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
                assert conn != null;
                conn.rollback();
            } catch (SQLException ignored) {
            }

        }
    }

    public static void getCurrentBalance() {
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT SUM(sum) AS Total FROM itemsdatabase.purchase;";
            Connection conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) System.out.println("Current cash register balance :" + rs.getDouble("total"));
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
            }
        }
    }
}

