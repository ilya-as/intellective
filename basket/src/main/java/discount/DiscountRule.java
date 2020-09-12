package main.java.discount;

import main.java.model.Item;

import java.math.BigDecimal;
import java.util.HashMap;

public interface DiscountRule {
    BigDecimal countDiscount(HashMap<Item, Integer> basketItems, BigDecimal preTotal);
    String getDescribe();
}
