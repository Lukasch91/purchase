import model.item.Item;



public class ManualScanner implements Scanner {

    public Item scan(Long itemCode) {

        ItemWorker itemWorker = new ItemWorker();
        return itemWorker.getItemByCode(itemCode);
    }
}
