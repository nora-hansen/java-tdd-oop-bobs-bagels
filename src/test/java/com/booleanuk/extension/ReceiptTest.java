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
                  ~~~ Bob's Bagels ~~~
                
                  2024-01-19  10:31:50
                
                ------------------------
                
                Onion Bagel     5  2.45
                
                ------------------------
                Total              2.45
                
                        Thank you
                     for your order!
                """;
        Assertions.assertEquals(expectedString, receipt.generateReceipt());
    }
}
