package model;

public class Product {
    private Integer id;
    private Integer sequence;

    public Product(Integer id, Integer sequence) {
        this.id = id;
        this.sequence = sequence;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSequence() {
        return sequence;
    }

    @Override
    public String toString() {
        return "" + id + ",";
    }
}
