
import model.item.*;
import java.util.ArrayList;

public class ItemWorker {

    private ArrayList<Item> itemsArrayList = new ArrayList<>();

    public ItemWorker() {
        DatabaseWorker databaseWorker = new DatabaseWorker();
        itemsArrayList = databaseWorker.loadItem();
    }

    public int getItemsArrayListLength() {
        return itemsArrayList.size();
    }



    public Item getItemByCode(Long itemCode) {
        for (Item item : itemsArrayList) {
            if (itemCode.equals(item.getItemCode())) {
                return item;
            }
        }
        return null;
    }

    public Item getItemByIndex(int randomNumber) {
        return itemsArrayList.get(randomNumber);
    }
}

