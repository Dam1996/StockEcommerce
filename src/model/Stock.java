package model;

public class Stock {
    private Integer sizeId;
    private Integer quantity;

    public Stock(Integer sizeId, Integer quantity) {
        this.sizeId = sizeId;
        this.quantity = quantity;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
