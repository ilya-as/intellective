
// implement domain model for using SOLID\GRASP. No GUI; No DB. Discounts should be done as separate classes.

basket:

-tool_1: 3 Х $7 = 21;
-tool_2: 2 X $10 = 20;
-tool_3: 100 X $0.01 = 1;

pre-total: $42;

discount 10% (there are 1 X tool_1 and 10 X tool_3) -$4.2
discount (there are 2 Х tool_2) -$5

total: $32.8;
