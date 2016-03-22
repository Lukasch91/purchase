package model.item;


public class AmountDiscount extends Discount {



    @Override
    public Double applyDiscount(Double price) {
        return price - amount;
    }
}


