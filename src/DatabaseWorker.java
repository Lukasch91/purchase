import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import model.item.*;

public class DatabaseWorker {

    public void addToFile(String printText) {

        BufferedWriter bufferWriter;
        FileWriter fileWriter;

        try {
            File file = new File("history.txt");
            fileWriter = new FileWriter(file.getName(), true);
            bufferWriter = new BufferedWriter(fileWriter);

            bufferWriter.write(printText);
            bufferWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Double> getBalanceRecords() {
        BufferedReader br;
        FileInputStream fstream;
        ArrayList<Double> cashBalanceArray = new ArrayList<>();

        try {
            fstream = new FileInputStream("history.txt");
            br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] parts = strLine.split(";");
                String stringName = parts[0];
                String amount = parts[1];
                if (stringName.equals("Withdrawal")) {
                    Double amountDouble = Double.parseDouble(amount);
                    cashBalanceArray.add(amountDouble);
                } else if (stringName.equals("Sum")) {
                    Double amountDouble = Double.parseDouble(amount);
                    cashBalanceArray.add(amountDouble);
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File Not Found");
        } catch (java.io.IOException exception) {
            System.out.println("Error");
        }
        return cashBalanceArray;
    }


    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/data";

    static final String USER = "root";
    static final String PASS = "kempas91";

    public ArrayList<Item> loadItem() {
        ArrayList<Item> itemsArrayList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT itemCode, itemName, itemPrice, typeOfDiscount, discountAmount FROM datatable";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Long itemCode = rs.getLong("itemCode");
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
                Item item = new Item(itemCode, itemName, itemPrice, discount);
                itemsArrayList.add(item);

            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return itemsArrayList;
    }
}