
public class Entry {
    Item item;
    Integer numberOfItems;

    public Entry(Item item, Integer numberOfItems) {
        this.item = item;
        this.numberOfItems = numberOfItems;
    }


    public Double sumOfItem() {
        Double sum = item.itemPrice * numberOfItems;
        return sum;
    }
}


