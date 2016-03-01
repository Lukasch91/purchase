import java.util.ArrayList;

public class Purchase {

    private ArrayList<Entry> itemsArrayList = new ArrayList<Entry>();


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

    public ArrayList<Entry> getItems() {
        return itemsArrayList;
    }
}
