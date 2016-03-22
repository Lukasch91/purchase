package model;

import model.item.Item;

public class Entry {
    private Item item;
    private Integer numberOfItems;


    public Entry(Item item, Integer numberOfItems) {
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


