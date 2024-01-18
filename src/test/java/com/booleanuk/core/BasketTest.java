package com.booleanuk.core;

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
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);

        Assertions.assertNotNull(onionBagel);
        Assertions.assertTrue(basket.addToBasket(inventory, onionBagel));
    }

    @Test
    public void testAddInvalidProduct()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product invalidBagel = new Product("INVL", inventory);

        Assertions.assertFalse(basket.addToBasket(inventory, invalidBagel));
    }

    @Test
    public void testAddValidProductBasketFull()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);

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
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);

        Assertions.assertNotNull(onionBagel);
        Assertions.assertTrue(basket.addToBasket(onionBagel, 3));
    }

    @Test
    public void testAddValidProductBasketTooSmall()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);
        basket.addToBasket(inventory, onionBagel);

        Assertions.assertNotNull(onionBagel);
        Assertions.assertFalse(basket.addToBasket(onionBagel, 3));
    }

    @Test
    public void testRemoveValidProduct()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);
        basket.addToBasket(inventory, onionBagel);

        Assertions.assertTrue(basket.removeFromBasket("BGLO"));
    }

    @Test
    public void testRemoveProductNotInBasket()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);
        basket.addToBasket(inventory, onionBagel);

        Assertions.assertFalse(basket.removeFromBasket("COFC"));
    }

    @Test
    public void testRemoveEmptyBasket()
    {
        Basket basket = new Basket();

        Assertions.assertFalse(basket.removeFromBasket("BGLO"));
    }

    @Test
    public void testRemoveProductShowsMessage()
    {
        System.setOut(new PrintStream(outputStreamCaptor));
        Basket basket = new Basket();
        basket.removeFromBasket("BGLO");
        String expectedString = "Product of SKU BGLO wasn't found in your basket!\n";

        Assertions.assertEquals(expectedString, outputStreamCaptor.toString());
        System.setOut(standardOut);
    }

    @Test
    public void testChangeBasketCapacitySuccess()
    {
        Basket basket = new Basket();

        Assertions.assertTrue(basket.changeSize(50));
        Assertions.assertTrue(basket.changeSize(100));
        Assertions.assertTrue(basket.changeSize(1));
    }

    @Test
    public void testChangeBasketCapacityInvalid()
    {
        Basket basket = new Basket();

        Assertions.assertFalse(basket.changeSize(-50));
        Assertions.assertFalse(basket.changeSize(-6));
        Assertions.assertFalse(basket.changeSize(-1000));
    }

    @Test
    public void testChangeBasketCapacityItemsAlreadyInBasketValid()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);
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
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);
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
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);
        Product coffeeLatte = new Product("COFL", inventory);
        Product cheese = new Product("FILC", inventory);

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
        Basket basket = new Basket();

        Assertions.assertEquals(0.0, basket.getTotal());
    }

    @Test
    public void testSeePrices()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();

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
        Basket basket = new Basket();
        Inventory inventory = new Inventory();

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
        Basket basket = new Basket();
        Inventory inventory = new Inventory();

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
        Basket basket = new Basket();
        Inventory inventory = new Inventory();

        ByteArrayInputStream testIn = new ByteArrayInputStream(("y".getBytes()));
        System.setIn(testIn);
        Assertions.assertTrue(basket.addToBasket(inventory,new Product("FILH", inventory)));
    }

    @Test
    public void testAddToBasketOutOfStock()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);

        inventory.setStock("BGLO", 0);
        Assertions.assertFalse(basket.addToBasket(inventory, onionBagel));
    }
}
