import model.Entry;
import model.item.Item;
import model.Purchase;


public class CashRegister {


    private Purchase purchase;
    private DatabaseWorker database = new DatabaseWorker();
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

    public void endPurchase() {
        for (Entry entry : purchase.getItems())
            if (entry != null) {
                System.out.println(entry.getItem().getItemName() + "..." + entry.getItem().getItemPrice() + "..." + entry.getNumberOfItems() +
                        "..." + entry.sumOfItem());

                String printText = entry.getItem().getItemName() + ";" + entry.getItem().getItemPrice() + ";" + entry.getNumberOfItems() +
                        ";" + entry.sumOfItem();

                database.addToFile(printText);
            }
        System.out.println("Sum:" + purchase.sumPrices());
        String printText = "Sum;" + purchase.sumPrices();
        database.addToFile(printText);
    }


    public void withdrawals() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Withdrawal sum:");
        Double withdrawal = scanner.nextDouble();
        String printText = "Withdrawal;" + -withdrawal;
        database.addToFile(printText);

    }


    public void currentCashBalance() {
        Double sum = 0.00;
        for (Double aGetFromFile : database.getBalanceRecords()) {
            sum = sum + aGetFromFile;
        }
        System.out.println("Current balance:" + sum);
    }


}





