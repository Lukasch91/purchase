package com.lukas.model.item;

public class PercentageDiscount extends Discount {

    public PercentageDiscount(Double amount) {
        super(amount);
    }

    @Override
    public Double applyDiscount(Double price) {
        return price - price * amount / 100;
    }
}
