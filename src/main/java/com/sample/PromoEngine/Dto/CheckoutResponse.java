package com.sample.PromoEngine.Dto;

public class CheckoutResponse {
    private int total;

    public CheckoutResponse(int total) {
        this.total = total;
    }

    public int getTotal() { return total; }
}