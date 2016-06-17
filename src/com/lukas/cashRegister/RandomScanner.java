package com.lukas.cashRegister;

import com.lukas.dao.DAOFactory;
import com.lukas.dao.ItemDAO;
import com.lukas.model.item.Item;

import java.util.Random;

public class RandomScanner implements Scanner {

    ItemDAO itemDao = DAOFactory.getItemDAO();

    public int getRandomNumber() {

        int max = itemDao.countItems();
        Random r = new Random();
        int randomNumber = r.nextInt(max);
        if (randomNumber == 0) {
            return getRandomNumber();
        }
        return randomNumber;
    }

    public Item scan(Long itemCode) {
        return itemDao.selectRandomItem(getRandomNumber());
    }
}



