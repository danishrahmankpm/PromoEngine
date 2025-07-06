package com.sample.PromoEngine.PromoEngine;

import java.util.List;

import com.sample.PromoEngine.Model.CartItem;

public class QuantityPromotion implements Promotion {
    private final char skuId;
    private final int quantityForPromo;
    private final int promoPrice;

    public QuantityPromotion(char skuId, int quantityForPromo, int promoPrice) {
        this.skuId = skuId;
        this.quantityForPromo = quantityForPromo;
        this.promoPrice = promoPrice;
    }

    @Override
    public int apply(List<CartItem> items) {
        int total = 0;
        for (CartItem item : items) {
            if (item.getSku().getId() == skuId) {
                int qty = item.getQuantity();
                int unitPrice = item.getSku().getPrice();
                total += (qty / quantityForPromo) * promoPrice + (qty % quantityForPromo) * unitPrice;
                item.setQuantity(0); // Mark as processed
            }
        }
        return total;
    }
}
