package cashRegister;

import model.Entry;
import model.item.Item;
import model.Purchase;

import java.sql.SQLException;


public class CashRegister {


    private Purchase purchase;
    private Scanner scanner;

    public CashRegister(Scanner scanner) {
        this.scanner = scanner;
    }

    public void scanItem(Long itemCode, Integer numberOfItems) {
        Item item = scanner.scan(itemCode);
        if (item == null) {
            System.out.println("Item not found");
            return;
        }
        Entry entry = new Entry(item, numberOfItems);
        purchase.addItemToList(entry);
    }

    public void scanItem(Integer numberOfItems) {
        Item item = scanner.scan(null);
        purchase.addItemToList(new Entry(item, numberOfItems));
    }

    public void startPurchase() {
        purchase = new Purchase();
    }

    public void endPurchase() throws SQLException, ClassNotFoundException {
        purchase.getItems().stream().filter(entry -> entry != null).forEach(entry -> System.out.println(entry.getItem().getItemName()
                + "..." + entry.getItem().getItemPrice() + "..." + entry.getNumberOfItems() +
                "..." + entry.sumOfItem()));
        System.out.println("Sum:" + purchase.sumPrices());
        dao.ItemDAO.addPurchaseToDatabase(purchase);
        dao.ItemDAO.addToDatabase(purchase);
    }


    public static double withdrawals() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Withdrawal sum:");
        return scanner.nextDouble();
    }

}





