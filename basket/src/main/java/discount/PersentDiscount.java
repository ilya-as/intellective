package main.java.discount;

import main.java.model.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PersentDiscount implements DiscountRule {
    private HashMap<Item, Integer> items = new HashMap<>();
    private BigDecimal persent;
    private BigDecimal discountSum;
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public PersentDiscount(HashMap<Item, Integer> items, BigDecimal persent) {
        this.items = items;
        this.persent = persent;
    }

    @Override
    public BigDecimal countDiscount(HashMap<Item, Integer> basketItems, BigDecimal preTotal) {
        discountSum = BigDecimal.ZERO;
        if (checkForDiscount(basketItems)) {
            discountSum = persent.multiply(preTotal.divide(ONE_HUNDRED));
        }
        return discountSum;
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

    public String getDescribe() {
        StringBuilder builder = new StringBuilder();
        if (discountSum.intValue() != 0) {
            builder.append("discount ");
            builder.append(persent.toString());
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
            builder.append(discountSum.toString());
        }
        return builder.toString();
    }
}
