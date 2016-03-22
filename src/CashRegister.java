import model.Entry;
import model.item.Item;
import model.Purchase;

import java.util.Scanner;

public class CashRegister {


    private Purchase purchase;
    private FileWorker fileWorker = new FileWorker();


    public void scanItem(Long itemCode, Integer numberOfItems) {
        ItemWorker itemWorker = new ItemWorker();
        Item item = itemWorker.getItemByCode(itemCode);
        Entry entry = new Entry(item, numberOfItems);
        purchase.addItemToList(entry);

    }

    public void startPurchase() {
        purchase = new Purchase();
    }

    public void endPurchase() {
        for (Entry entry : purchase.getItems())
            if (entry != null) {
                System.out.println(entry.getItem().getItemName() + "..." + entry.getItem().getItemPrice() + "..." + entry.getNumberOfItems() +
                        "..." + entry.sumOfItem());

                String printText = entry.getItem().getItemName() + ";" + entry.getItem().getItemPrice() + ";" + entry.getNumberOfItems() +
                        ";" + entry.sumOfItem();

                fileWorker.addToFile(printText);
            }
        System.out.println("Sum:" + purchase.sumPrices());
        String printText = "Sum;" + purchase.sumPrices();
        fileWorker.addToFile(printText);
    }


    public void withdrawals() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Withdrawal sum:");
        Double withdrawal = scanner.nextDouble();
        String printText = "Withdrawal;" + -withdrawal;
        fileWorker.addToFile(printText);

    }


    public void currentCashBalance() {
        fileWorker.getFromFile();
        Double sum = 0.00;
        for (Double aGetFromFile : fileWorker.getFromFile()) {
            sum = sum + aGetFromFile;
        }
        System.out.println("Current balance:" + sum);
    }


}





