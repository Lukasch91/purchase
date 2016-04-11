import model.item.*;

import java.io.*;
import java.util.ArrayList;


public class FileWorker {


    public void addToFile(String printText) {

        BufferedWriter bufferWriter = null;
        FileWriter fileWriter = null;

        try {
            File file = new File("history.txt");
            fileWriter = new FileWriter(file.getName(), true);
            bufferWriter = new BufferedWriter(fileWriter);

            bufferWriter.write(printText);
            bufferWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeWriteResources(bufferWriter, fileWriter);
        }
    }

    public ArrayList<Double> getBalanceRecords() {
        BufferedReader br = null;
        FileInputStream fstream = null;
        ArrayList<Double> cashBalanceArray = new ArrayList<Double>();

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
        } finally {
            closeReadResources(br, fstream);
        }
        return cashBalanceArray;
    }

    public ArrayList<Item> loadItem() {
        ArrayList<Item> itemsArrayList = new ArrayList<Item>();
        BufferedReader br = null;
        try {
            FileInputStream fstream = new FileInputStream("C:/Hello_world/data.txt");
            br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] parts = strLine.split(";");
                String code = parts[0];
                String name = parts[1];
                String price = parts[2];
                Long codeLong = Long.parseLong(code);
                Double priceDouble = Double.parseDouble(price);
                Discount discount = null;
                if (parts.length > 3) {
                    String discountType = parts[3];
                    String discountAmount = parts[4];

                    Double doubleDiscountAmount = Double.parseDouble(discountAmount);

                    if (doubleDiscountAmount < 0) {
                        discount = new NoDiscount();
                    }

                    if ("Z".equals(discountType)) {
                        discount = new AmountDiscount(doubleDiscountAmount);
                    } else if ("F".equals(discountType)) {
                        discount = new PercentageDiscount(doubleDiscountAmount);
                    }

                }

                if (discount == null) {
                    discount = new NoDiscount();
                }
                Item item = new Item(codeLong, name, priceDouble, discount);
                itemsArrayList.add(item);
            }
            return itemsArrayList;

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            throw new RuntimeException(e);
        } catch (java.io.IOException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (java.io.IOException exception) {
                    System.out.println("Error");
                }
            }
        }
    }

    private void closeReadResources(BufferedReader br, FileInputStream fstream) {
        try {
            if (br != null) {
                br.close();
            }
            if (fstream != null) {
                fstream.close();
            }
        } catch (java.io.IOException exception) {
            System.out.println("Error");
        }
    }


    private void closeWriteResources(BufferedWriter bw, FileWriter fw) {
        try {
            if (bw != null) {
                bw.close();
            }
            if (fw != null) {
                fw.close();
            }
        } catch (java.io.IOException exception) {
            System.out.println("Error");
        }
    }
}
