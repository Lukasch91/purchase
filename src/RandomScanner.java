import model.item.Item;

import java.util.Random;

public class RandomScanner implements Scanner {
    ItemWorker itemWorker = new ItemWorker();


    public Item scan(Long itemCode) {
        return itemWorker.getItemByIndex(getRandomNumber());
    }

    public int getRandomNumber() {


        int min = 0;
        int max = itemWorker.getItemsArrayListLength();

        Random r = new Random();

        return r.nextInt(max);
    }


}



