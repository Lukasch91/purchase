package com.lukas.model.item;

public class Item {
    private String itemName;
    private Double itemPrice;
    private Discount discount;


    public Item(String itemName, Double itemPrice, Discount discount) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discount = discount;
    }


    public String getItemName() {
        return itemName;
    }

    public Double getItemPrice() {
        Double priceWithDiscount = discount.calculateDiscount(itemPrice);
        if (priceWithDiscount < 0) {
            throw new RuntimeException("Item price under 0.00");
        }
        return priceWithDiscount;
    }
}

