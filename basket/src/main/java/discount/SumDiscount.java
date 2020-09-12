package main.java.discount;

import main.java.model.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SumDiscount implements DiscountRule {
    private HashMap<Item, Integer> items = new HashMap<>();
    private BigDecimal discountCount;
    private BigDecimal discountResult;

    public SumDiscount(HashMap<Item, Integer> items, BigDecimal discountCount) {
        this.items = items;
        this.discountCount = discountCount;
    }

    @Override
    public BigDecimal countDiscount(HashMap<Item, Integer> basketItems, BigDecimal preTotal) {
        discountResult = BigDecimal.ZERO;
        if (checkForDiscount(basketItems)) {
            discountResult = discountCount;
        }
        return discountResult;
    }

    private boolean checkForDiscount(HashMap<Item, Integer> basketItems) {
        boolean result = true;
        Set<Map.Entry<Item, Integer>> set = items.entrySet();

        for (Map.Entry<Item, Integer> entry : set) {
            if (!basketItems.containsKey(entry.getKey())) {
                return false;
            }

            if (basketItems.get(entry.getKey()).compareTo(entry.getValue()) < 0) {
                return false;
            }
        }
        return result;
    }

    @Override
    public String getDescribe() {
        StringBuilder builder = new StringBuilder();
        if (discountResult.intValue() != 0) {
            builder.append("discount ");
            builder.append("% (there are ");
            Set<Map.Entry<Item, Integer>> set = items.entrySet();
            boolean firtIteration = true;
            for (Map.Entry<Item, Integer> entry : set) {
                if (!firtIteration) {
                    builder.append(" and ");
                }
                builder.append(entry.getValue());
                builder.append(" X ");
                builder.append(entry.getKey().getName());
                firtIteration = false;
            }
            builder.append(") -$");
            builder.append(discountResult.toString());
        }
        return builder.toString();
    }
}
