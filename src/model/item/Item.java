package model.item;

public class Item {
    private Long itemCode;
    private String itemName;
    private Double itemPrice;
    private Discount discount;


    public Item(Long itemCode, String itemName, Double itemPrice, Discount discount) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discount = discount;
    }

    public Long getItemCode() {
        return itemCode;
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

