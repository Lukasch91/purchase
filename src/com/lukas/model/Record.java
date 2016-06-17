package com.lukas.model;

import com.lukas.model.item.Item;

public class Record {
    private Item item;
    private Integer numberOfItems;


    public Record(Item item, Integer numberOfItems) {
        this.item = item;
        this.numberOfItems = numberOfItems;
    }


    public Double sumOfItem() {
        return item.getItemPrice() * numberOfItems;
    }

    public Item getItem() {
        return item;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }
}


