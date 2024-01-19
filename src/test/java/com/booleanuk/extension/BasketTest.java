package com.booleanuk.extension;

import com.booleanuk.core.Basket;
import com.booleanuk.core.Inventory;
import com.booleanuk.core.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BasketTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void testAddValidProduct()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);

        Assertions.assertNotNull(onionBagel);
        Assertions.assertTrue(basket.addToBasket(inventory, onionBagel));
    }

    @Test
    public void testAddInvalidProduct()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product invalidBagel = new com.booleanuk.core.Product("INVL", inventory);

        Assertions.assertFalse(basket.addToBasket(inventory, invalidBagel));
    }

    @Test
    public void testAddValidProductBasketFull()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);

        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);

        Assertions.assertFalse(basket.addToBasket(inventory, onionBagel));
    }

    @Test
    public void testAddValidProductThreeTimes()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);

        Assertions.assertNotNull(onionBagel);
        Assertions.assertTrue(basket.addToBasket(onionBagel, 3, inventory));
    }

    @Test
    public void testAddValidProductBasketTooSmall()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);

        Assertions.assertNotNull(onionBagel);
        Assertions.assertFalse(basket.addToBasket(onionBagel, 3, inventory));
    }

    @Test
    public void testRemoveValidProduct()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);
        basket.addToBasket(inventory, onionBagel);

        Assertions.assertTrue(basket.removeFromBasket("BGLO"));
    }

    @Test
    public void testRemoveProductNotInBasket()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);
        basket.addToBasket(inventory, onionBagel);

        Assertions.assertFalse(basket.removeFromBasket("COFC"));
    }

    @Test
    public void testRemoveEmptyBasket()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();

        Assertions.assertFalse(basket.removeFromBasket("BGLO"));
    }

    @Test
    public void testRemoveProductShowsMessage()
    {
        System.setOut(new PrintStream(outputStreamCaptor));
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        basket.removeFromBasket("BGLO");
        String expectedString = "Product of SKU BGLO wasn't found in your basket!\n";

        Assertions.assertEquals(expectedString, outputStreamCaptor.toString());
        System.setOut(standardOut);
    }

    @Test
    public void testChangeBasketCapacitySuccess()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();

        Assertions.assertTrue(basket.changeSize(50));
        Assertions.assertTrue(basket.changeSize(100));
        Assertions.assertTrue(basket.changeSize(1));
    }

    @Test
    public void testChangeBasketCapacityInvalid()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();

        Assertions.assertFalse(basket.changeSize(-50));
        Assertions.assertFalse(basket.changeSize(-6));
        Assertions.assertFalse(basket.changeSize(-1000));
    }

    @Test
    public void testChangeBasketCapacityItemsAlreadyInBasketValid()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);

        Assertions.assertTrue(basket.changeSize(50));
        Assertions.assertTrue(basket.changeSize(100));
        Assertions.assertTrue(basket.changeSize(3));
    }

    @Test
    public void testChangeBasketCapacityItemsAlreadyInBasketNotValid()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);

        Assertions.assertFalse(basket.changeSize(0));
        Assertions.assertFalse(basket.changeSize(1));
        Assertions.assertFalse(basket.changeSize(2));
    }

    @Test
    public void testGetTotalCost()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);
        com.booleanuk.core.Product coffeeLatte = new com.booleanuk.core.Product("COFL", inventory);
        com.booleanuk.core.Product cheese = new com.booleanuk.core.Product("FILC", inventory);

        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, coffeeLatte);
        basket.addToBasket(inventory, cheese);

        Assertions.assertEquals(1.9, basket.getTotal());

        basket.addToBasket(inventory, onionBagel);
        Assertions.assertEquals(2.39, basket.getTotal());
    }

    @Test
    public void testGetTotalCostEmptyBasket()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();

        Assertions.assertEquals(0.0, basket.getTotal());
    }

    @Test
    public void testSeePrices()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();

        String expectedString = """
                Prices:
                Onion Bagel - 0.49
                Plain Bagel - 0.39
                Everything Bagel - 0.49
                Sesame Bagel - 0.49
                Black Coffee - 0.99
                White Coffee - 1.19
                Cappuccino Coffee - 1.29
                Latte Coffee - 1.29
                Bacon Filling - 0.12
                Egg Filling - 0.12
                Cheese Filling - 0.12
                Cream Cheese Filling - 0.12
                Smoked Salmon Filling - 0.12
                Ham Filling - 0.12
                """;

        System.setOut(new PrintStream(outputStreamCaptor));
        basket.showPrices(inventory);
        Assertions.assertEquals(expectedString, outputStreamCaptor.toString());
        System.setOut(standardOut);
    }

    @Test
    public void testSeePriceOfOneItem()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();

        String expectedString = """
                Prices:
                Latte Coffee - 1.29
                """;

        System.setOut(new PrintStream(outputStreamCaptor));
        basket.showPrices("COFL", inventory);
        Assertions.assertEquals(expectedString, outputStreamCaptor.toString());
        System.setOut(standardOut);
    }

    @Test
    public void testSeePriceOfMultipleItems()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();

        String expectedString = """
                Prices:
                Latte Coffee - 1.29
                Ham Filling - 0.12
                Everything Bagel - 0.49
                """;

        System.setOut(new PrintStream(outputStreamCaptor));
        basket.showPrices(new String[]{"COFL", "FILH", "BGLE"}, inventory);
        Assertions.assertEquals(expectedString, outputStreamCaptor.toString());
        System.setOut(standardOut);
    }

    @Test
    public void testAddFillingsNoBagel()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();

        ByteArrayInputStream testIn = new ByteArrayInputStream(("y".getBytes()));
        System.setIn(testIn);
        Assertions.assertTrue(basket.addToBasket(inventory,new com.booleanuk.core.Product("FILH", inventory)));
    }

    @Test
    public void testAddToBasketOutOfStock()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);

        inventory.setStock("BGLO", 0);
        Assertions.assertFalse(basket.addToBasket(inventory, onionBagel));
    }

    @Test
    public void testSixBagelDiscount()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);

        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        Assertions.assertEquals(2.49, basket.getTotal());
    }

    @Test
    public void testTwelveBagelDiscount()
    {
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket();
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);

        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);

        Assertions.assertEquals(3.99, basket.getTotal());
    }

    @Test
    public void testCoffeeBagelDiscount()
    {
        com.booleanuk.core.Basket basket = new Basket();
        com.booleanuk.core.Inventory inventory = new Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);
        com.booleanuk.core.Product blackCoffee = new Product("COFB", inventory);

        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, blackCoffee);

        Assertions.assertEquals(1.25, basket.getTotal());
    }
}
