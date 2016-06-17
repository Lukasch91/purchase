package com.lukas.cashRegister;

import com.lukas.service.PurchaseService;
import com.lukas.model.Record;
import com.lukas.model.item.Item;
import com.lukas.model.Purchase;

import java.sql.SQLException;


public class CashRegister {


    private Purchase purchase;
    private Scanner scanner;
    private PurchaseService PurchaseService = new PurchaseService();

    public CashRegister(PurchaseService PurchaseService) {
        this.PurchaseService = PurchaseService;
    }

    public void scanItem(Long itemCode, Integer numberOfItems) {
        Item item = scanner.scan(itemCode);
        if (item == null) {
            System.out.println("Item not found");
            return;
        }
        Record record = new Record(item, numberOfItems);
        purchase.addItemToList(record);
    }

    public void scanItem(Integer numberOfItems) {
        Item item = scanner.scan(null);
        purchase.addItemToList(new Record(item, numberOfItems));
    }

    public void startPurchase() {
        purchase = new Purchase();
    }

    public void endPurchase() throws SQLException, ClassNotFoundException {
        purchase.getRecords().stream().filter(entry -> entry != null).forEach(entry -> System.out.println(entry.getItem().getItemName()
                + "..." + entry.getItem().getItemPrice() + "..." + entry.getNumberOfItems() +
                "..." + entry.sumOfItem()));
        System.out.println("Sum:" + purchase.sumPrices());
        PurchaseService.addPurchasesAndItemsToDatabase(purchase);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}





