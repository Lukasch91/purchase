package model;

import model.Entry;

import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private List<Entry> itemsArrayList = new ArrayList<Entry>();


    public void addItemToList(Entry entry) {
        itemsArrayList.add(entry);
    }

    public Double sumPrices() {
        Double sum = 0.00;
        for (Entry entry : itemsArrayList) {
            sum = sum + entry.sumOfItem();
        }

        return sum;
    }




    public List<Entry> getItems() {
        return itemsArrayList;
    }
}
