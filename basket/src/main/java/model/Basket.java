package main.java.model;

import main.java.discount.DiscountRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Basket {
    private HashMap<Item, Integer> items = new HashMap<>();
    private ArrayList<DiscountRule> discountRules = new ArrayList<>();
    private BigDecimal preTotal;
    private BigDecimal total;

    public Basket(ArrayList<DiscountRule> discountRules) {
        this.discountRules = discountRules;
    }

    public Basket() {
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public BigDecimal getPreTotal() {
        return preTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void addItem(Item item, int count) {
        if (items.containsKey(item)) {
            count = items.get(item) + count;
        }
        items.put(item, count);
    }

    public void calculate() {
        preTotal = BigDecimal.ZERO;
        Set<Map.Entry<Item, Integer>> set = items.entrySet();
        for (Map.Entry<Item, Integer> entry : set) {
            preTotal = preTotal.add(entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())));
        }

        BigDecimal discountSum = BigDecimal.ZERO;
        for (DiscountRule rule : discountRules) {
            discountSum = discountSum.add(rule.countDiscount(items, preTotal));
        }

        total = preTotal.subtract(discountSum);
    }

    public String getDescribe() {
        StringBuilder builder = new StringBuilder();
        builder.append("basket:");
        builder.append(System.getProperty("line.separator"));
        Set<Map.Entry<Item, Integer>> set = items.entrySet();
        for (Map.Entry<Item, Integer> entry : set) {
            builder.append(entry.getKey().getName());
            builder.append(": ");
            builder.append(entry.getValue());
            builder.append(" X ");
            builder.append(entry.getKey().getPrice());
            builder.append(" = ");
            builder.append(entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())));
            builder.append(";");
            builder.append(System.getProperty("line.separator"));
        }
        builder.append("pre-total: $");
        builder.append(preTotal);

        for (DiscountRule rule : discountRules) {
            builder.append(System.getProperty("line.separator"));
            builder.append(rule.getDescribe());
        }

        builder.append(System.getProperty("line.separator"));
        builder.append("total: $");
        builder.append(total);
        return builder.toString();
    }
}
