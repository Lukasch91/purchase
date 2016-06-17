package com.lukas.model.item;

public class NoDiscount extends Discount {


    public NoDiscount() {
        super(0.00);

    }

    @Override
    public Double applyDiscount(Double price) {
        return price;
    }
}
