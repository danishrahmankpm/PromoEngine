package com.sample.PromoEngine.PromoEngine;
import java.util.List;
import com.sample.PromoEngine.Model.CartItem;

public class PromotionEngine {
    private final List<Promotion> promotions;

    public PromotionEngine(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public int calculateTotal(List<CartItem> cartItems) {
        int total = 0;
        for (Promotion promo : promotions) {
            total += promo.apply(cartItems);
        }
        for (CartItem item : cartItems) {
            total += item.getQuantity() * item.getSku().getPrice();
        }
        return total;
    }
}
