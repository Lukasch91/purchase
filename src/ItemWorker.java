import model.item.AmountDiscount;
import model.item.Discount;
import model.item.Item;
import model.item.PercentageDiscount;

import java.io.*;
import java.util.ArrayList;

public class ItemWorker {

    private ArrayList<Item> itemsArrayList = new ArrayList<Item>();

    public ItemWorker() {
        loadItem();
    }

    private void loadItem() {
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
                String discountType = parts[3];
                String discountAmount = parts[4];
                Double doubleDiscountAmount = Double.parseDouble(discountAmount);
                Long codeLong = Long.parseLong(code);
                Double priceDouble = Double.parseDouble(price);


                Discount discount = null;
                if("Z".equals(discountType)) {
                     discount = new AmountDiscount();
                }else if ("F".equals(discountType)) {
                     discount = new PercentageDiscount();

                } else {
                    discount = new Discount();
                }
                Item item = new Item(codeLong, name, priceDouble, discount);
                itemsArrayList.add(item);

            }
        } catch (FileNotFoundException exception) {
            System.out.println("File Not Found");
        } catch (java.io.IOException exception) {
            System.out.println("Error");
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


    public Item getItemByCode(Long itemCode) {
        for (Item item : itemsArrayList) {
            if (itemCode.equals(item.getItemCode())) {
                return item;
            }
        }
        return null;
    }
}

