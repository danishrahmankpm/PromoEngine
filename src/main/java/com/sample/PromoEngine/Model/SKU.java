package com.sample.PromoEngine.Model;

public class SKU {
    private final char id;
    private final int price;

    public SKU(char id, int price) {
        this.id = id;
        this.price = price;
    }

    public char getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }
}
