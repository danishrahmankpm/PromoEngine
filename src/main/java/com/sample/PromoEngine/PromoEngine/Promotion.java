package com.sample.PromoEngine.PromoEngine;
import java.util.List;
import com.sample.PromoEngine.Model.CartItem;



public interface Promotion {

    int apply(List<CartItem> items);

}