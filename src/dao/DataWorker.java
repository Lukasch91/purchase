package dao;

import model.item.Item;

interface DataWorker {

    Item loadItem(Long itemCode);


}
