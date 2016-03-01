public class CashRegister {


    private Purchase purchase;

    public void scan(Long itemCode, Integer numberOfItems) {
        ItemWorker itemWorker = new ItemWorker();
        Item item = itemWorker.getItemByCode(itemCode);
        Entry entry = new Entry(item, numberOfItems);
        purchase.addItemToList(entry);

    }

    public void startPurchase() {

        purchase = new Purchase();
    }

    public void printBill() {
        HistoryOfPurchases hop = new HistoryOfPurchases();
        for (Entry entry : purchase.getItems())
            if (entry != null) {

                System.out.println(entry.item.itemName + "..." + entry.item.itemPrice + "..." + entry.numberOfItems +
                        "..." + entry.sumOfItem());

                hop.printText = entry.item.itemName + "..." + entry.item.itemPrice + "..." + entry.numberOfItems +
                        "..." + entry.sumOfItem();

                hop.historyOfJava(hop.printText);

            }
        System.out.println("Sum:" + purchase.sumPrices());
        hop.printText = "Sum:" + purchase.sumPrices();
        hop.historyOfJava(hop.printText);
        
    }
}


