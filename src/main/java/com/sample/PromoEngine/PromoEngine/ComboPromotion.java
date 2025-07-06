package com.sample.PromoEngine.PromoEngine;
import java.util.List;
import com.sample.PromoEngine.Model.CartItem;

public class ComboPromotion implements Promotion {
    private final char skuId1, skuId2;
    private final int comboPrice;

    public ComboPromotion(char skuId1, char skuId2, int comboPrice) {
        this.skuId1 = skuId1;
        this.skuId2 = skuId2;
        this.comboPrice = comboPrice;
    }

    @Override
    public int apply(List<CartItem> items) {
        CartItem item1 = null, item2 = null;
        for (CartItem item : items) {
            if (item.getSku().getId() == skuId1) item1 = item;
            if (item.getSku().getId() == skuId2) item2 = item;
        }

        if (item1 == null || item2 == null) return 0;

        int combos = Math.min(item1.getQuantity(), item2.getQuantity());
        int total = combos * comboPrice;
        item1.setQuantity(item1.getQuantity() - combos);
        item2.setQuantity(item2.getQuantity() - combos);

        return total;
    }
}

