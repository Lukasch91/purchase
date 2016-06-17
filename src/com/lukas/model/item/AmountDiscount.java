package com.lukas.model.item;


public class AmountDiscount extends Discount {

    public AmountDiscount(Double amount) {
        super(amount);
    }

    @Override
    public Double applyDiscount(Double price) {
        return price - amount;
    }
}


