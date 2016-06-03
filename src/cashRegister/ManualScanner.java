package cashRegister;

import dao.ItemDAO;
import model.item.Item;


public class ManualScanner implements Scanner {

    public Item scan(Long itemCode) {

        ItemDAO databaseWorker = new ItemDAO();
        return databaseWorker.loadItem(itemCode);
    }
}
