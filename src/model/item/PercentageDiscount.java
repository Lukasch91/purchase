package model.item;

public class PercentageDiscount extends Discount {



    @Override
    public Double applyDiscount(Double price) {
        return price - price * discountPrecentage;
    }
}
