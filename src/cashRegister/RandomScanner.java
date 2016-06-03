package cashRegister;

import dao.ItemDAO;
import model.item.Item;

import java.util.Random;

public class RandomScanner implements Scanner {

    ItemDAO databaseWorker = new ItemDAO();


    public int getRandomNumber() {

        int max = databaseWorker.countItems();
        Random r = new Random();
        int randomNumber = r.nextInt(max);
        if (randomNumber == 0) {
            return getRandomNumber();
        }
        return randomNumber;
    }

    public Item scan(Long itemCode) {
        return databaseWorker.selectRandomItem(getRandomNumber());
    }
}



