package com.sample.PromoEngine;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.PromoEngine.Model.SKU;
import com.sample.PromoEngine.PromoEngine.ComboPromotion;
import com.sample.PromoEngine.PromoEngine.Promotion;
import com.sample.PromoEngine.PromoEngine.PromotionEngine;
import com.sample.PromoEngine.PromoEngine.QuantityPromotion;

@SpringBootTest
class PromoEngineApplicationTests {

	@Test
	void contextLoads() {
	}

	private PromotionEngine engine;
    private Map<String, SKU> skuMap;

    @BeforeEach
    void setup() {
       
        skuMap = Map.of(
            "A", new SKU('A', 50),
            "B", new SKU('B', 30),
            "C", new SKU('C', 20),
            "D", new SKU('D', 15)
        );

        
        List<Promotion> promotions = List.of(
            new QuantityPromotion('A', 3, 130),
            new QuantityPromotion('B', 2, 45),
            new ComboPromotion('C', 'D', 30)
        );

        engine = new PromotionEngine(promotions);
    }

}
