package com.booleanuk.extension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReceiptTest {
    @Test
    public void testGenerateReceipt()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Bagel("BGLO", inventory);
        Product whiteCoffee = new Coffee("COFW", inventory);
        Product cheese = new Filling("FILC", inventory);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(cheese);
        basket.addToBasket(cheese);
        basket.addToBasket(whiteCoffee);
        Receipt receipt = new Receipt(basket, inventory);

        String expectedString = """
                     ~~~ Bob's Bagels ~~~    \s
                                  
                \s\s\s\s\s""";
        expectedString += receipt.getDateTime();
        expectedString += """
                    \s
                        
                ------------------------------

                White Coffee           1 £1.19
                Onion Bagel            4 £1.96
                Cheese Filling         2 £0.24

                ------------------------------
                Total                    £3.39


                          Thank you         \s
                       for your order!      \s""";
        Assertions.assertEquals(expectedString, receipt.generateReceipt());
    }

    @Test
    public void testGenerateEmptyReceipt()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Receipt receipt = new Receipt(basket, inventory);

        String expectedString = """
                     ~~~ Bob's Bagels ~~~    \s
                                  
                \s\s\s\s\s""";
        expectedString += receipt.getDateTime();
        expectedString += """
                    \s
                        
                ------------------------------


                ------------------------------
                Total                     £0.0


                          Thank you         \s
                       for your order!      \s""";
        Assertions.assertEquals(expectedString, receipt.generateReceipt());
    }
}
