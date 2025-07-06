package com.sample.PromoEngine.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.PromoEngine.Dto.CheckoutResponse;
import com.sample.PromoEngine.Model.CartItem;
import com.sample.PromoEngine.Model.CartItemRequest;
import com.sample.PromoEngine.Model.SKU;
import com.sample.PromoEngine.PromoEngine.ComboPromotion;
import com.sample.PromoEngine.PromoEngine.Promotion;
import com.sample.PromoEngine.PromoEngine.PromotionEngine;
import com.sample.PromoEngine.PromoEngine.QuantityPromotion;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final Map<String, SKU> skuMap = Map.of(
        "A", new SKU('A', 50),
        "B", new SKU('B', 30),
        "C", new SKU('C', 20),
        "D", new SKU('D', 15)
    );

    private final PromotionEngine engine;

    public CheckoutController() {
        List<Promotion> promotions = List.of(
            new QuantityPromotion('A', 3, 130),
            new QuantityPromotion('B', 2, 45),
            new ComboPromotion('C', 'D', 30)
        );
        this.engine = new PromotionEngine(promotions);
    }

    @PostMapping
    public CheckoutResponse checkout(@RequestBody List<CartItemRequest> request) {
        List<CartItem> cartItems = new ArrayList<>();

        for (CartItemRequest item : request) {
            SKU sku = skuMap.get(item.getSku().toUpperCase());
            if (sku != null) {
                cartItems.add(new CartItem(sku, item.getQuantity()));
            }
        }

        int total = engine.calculateTotal(cartItems);
        return new CheckoutResponse(total);
    }
}


