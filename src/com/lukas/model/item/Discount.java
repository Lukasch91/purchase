package com.lukas.model.item;

public abstract class Discount {


    protected Double amount;

    public Discount(Double amount) {
        this.amount = amount;
    }

    public double calculateDiscount(Double price) {
        Double priceWithDiscount = applyDiscount(price);
        if (priceWithDiscount < 0) {
            throw new RuntimeException("Item price under 0.00");
        }
        return priceWithDiscount;


    }

    protected abstract  Double applyDiscount(Double price);

}



