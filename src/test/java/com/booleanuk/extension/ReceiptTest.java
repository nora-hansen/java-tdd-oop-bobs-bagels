package com.booleanuk.extension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReceiptTest {
    @Test
    public void testGenerateReceipt()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);
        Product whiteCoffee = new Product("COFW", inventory);
        Product cheese = new Product("FILC", inventory);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, cheese);
        basket.addToBasket(inventory, cheese);
        basket.addToBasket(inventory, whiteCoffee);
        Receipt receipt = new Receipt(basket, inventory);

        String expectedString = """
                     ~~~ Bob's Bagels ~~~    \s
                                  
                \s\s\s\s\s""";
        expectedString += receipt.getDateTime();
        expectedString += """
                    \s
                        
                ------------------------------

                White Coffee            1 1.19
                Onion Bagel             4 1.96
                Cheese Filling          2 0.24

                ------------------------------
                Total                     3.39


                          Thank you         \s
                       for your order!      \s""";
        Assertions.assertEquals(expectedString, receipt.generateReceipt());
    }

    @Test
    public void testGenerateEmptyReceipt()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Receipt receipt = new Receipt(basket, inventory);

        String expectedString = """
                     ~~~ Bob's Bagels ~~~    \s
                                  
                \s\s\s\s\s""";
        expectedString += receipt.getDateTime();
        expectedString += """
                    \s
                        
                ------------------------------


                ------------------------------
                Total                      0.0


                          Thank you         \s
                       for your order!      \s""";
        Assertions.assertEquals(expectedString, receipt.generateReceipt());
    }
}
