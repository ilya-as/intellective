package main.java;

import main.java.discount.DiscountRule;
import main.java.discount.PersentDiscount;
import main.java.discount.SumDiscount;
import main.java.model.Basket;
import main.java.model.Item;
import main.java.view.IPrinter;
import main.java.view.Printer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
       // -tool_1: 3 Х $7 = 21;
       // -tool_2: 2 X $10 = 20;
       // -tool_3: 100 X $0.01 = 1
        Item tool1 = new Item("tool_1",new BigDecimal("7.0"));
        Item tool2 = new Item("tool_2",new BigDecimal("10.0"));
        Item tool3 = new Item("tool_3",new BigDecimal("0.01"));

        ArrayList<DiscountRule> discountRules = new ArrayList<>();

        HashMap<Item, Integer> persentDiscountItems = new HashMap<>();
        persentDiscountItems.put(tool1,1);
        persentDiscountItems.put(tool3,10);
        //create discount system 10% (there are 1 X tool_1 and 10 X tool_3)
        DiscountRule persentDiscount = new PersentDiscount(persentDiscountItems,new BigDecimal(10));
        discountRules.add(persentDiscount);

        //create discount system sum discount (there are 2 Х tool_2) -$5
        HashMap<Item, Integer> sumDiscountItems = new HashMap<>();
        sumDiscountItems.put(tool2,2);
        DiscountRule sumDiscount = new SumDiscount(sumDiscountItems,new BigDecimal(5));
        discountRules.add(sumDiscount);

        Basket basket = new Basket(discountRules);
        basket.addItem(tool1,3);
        basket.addItem(tool2,2);
        basket.addItem(tool3,100);
        basket.calculate();

        IPrinter printer = new Printer();
        printer.print(basket.getDescribe());
    }
}
