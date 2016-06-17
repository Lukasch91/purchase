package com.lukas.cashRegister;

import com.lukas.dao.DAOFactory;
import com.lukas.model.item.Item;


public class ManualScanner implements Scanner {

    public Item scan(Long itemCode) {
        return DAOFactory.getItemDAO().loadItem(itemCode);
    }
}
