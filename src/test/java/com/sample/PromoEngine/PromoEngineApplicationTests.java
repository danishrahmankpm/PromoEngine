package com.sample.PromoEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.PromoEngine.Model.CartItem;
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
	
	@Test
    void testScenarioA() {
        // 1 A, 1 B, 1 C => 50 + 30 + 20 = 100
        List<CartItem> cart = List.of(
            new CartItem(skuMap.get("A"), 1),
            new CartItem(skuMap.get("B"), 1),
            new CartItem(skuMap.get("C"), 1)
        );

        int total = engine.calculateTotal(cart);
        assertEquals(100, total);
    }
	@Test
    void testScenarioB() {
        // 5 A => (3A=130) + (2A=100) = 230
        // 5 B => (2B=45) x2 + 1B=30 = 120
        // 1 C = 20
        List<CartItem> cart = List.of(
            new CartItem(skuMap.get("A"), 5),
            new CartItem(skuMap.get("B"), 5),
            new CartItem(skuMap.get("C"), 1)
        );

        int total = engine.calculateTotal(cart);
        assertEquals(370, total);
    }
	@Test
    void testScenarioC() {
        // 3 A = 130
        // 5 B = (2B=45) x2 + 1B=30 = 120
        // C + D = 30
        List<CartItem> cart = List.of(
            new CartItem(skuMap.get("A"), 3),
            new CartItem(skuMap.get("B"), 5),
            new CartItem(skuMap.get("C"), 1),
            new CartItem(skuMap.get("D"), 1)
        );

        int total = engine.calculateTotal(cart);
        assertEquals(280, total);
    }

	@Test
    void testScenarioD() {
        // 2 A = 100
        // 5 B = (2B=45) x2 + 1B=30 = 120
        // 3c + 2D = 30*2+ 20= 80
        List<CartItem> cart = List.of(
            new CartItem(skuMap.get("A"), 2),
            new CartItem(skuMap.get("B"), 5),
            new CartItem(skuMap.get("C"), 3),
            new CartItem(skuMap.get("D"), 2)
        );

        int total = engine.calculateTotal(cart);
        assertEquals(300, total);
    }
	@Test
    void testOnlyQuantityPromotion() {
        // 6 A => (3A=130) x2 = 260
        List<CartItem> cart = List.of(
            new CartItem(skuMap.get("A"), 6)
        );

        int total = engine.calculateTotal(cart);
        assertEquals(260, total);
    }
	@Test
    void testOnlyComboPromotion() {
        // 2 C + 1 D => (1C+1D=30), 1C=20 → 30 + 20 = 50
        List<CartItem> cart = List.of(
            new CartItem(skuMap.get("C"), 2),
            new CartItem(skuMap.get("D"), 1)
        );

        int total = engine.calculateTotal(cart);
        assertEquals(50, total);
    }
	


}
