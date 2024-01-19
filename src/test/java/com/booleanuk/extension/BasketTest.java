package com.booleanuk.extension;

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
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);

        Assertions.assertNotNull(onionBagel);
        Assertions.assertTrue(basket.addToBasket(onionBagel));
    }

    @Test
    public void testAddInvalidProduct()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product invalidBagel = new Product("INVL", inventory);

        Assertions.assertFalse(basket.addToBasket(invalidBagel));
    }

    @Test
    public void testAddValidProductBasketFull()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);

        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);

        Assertions.assertFalse(basket.addToBasket(onionBagel));
    }

    @Test
    public void testAddValidProductThreeTimes()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);

        Assertions.assertNotNull(onionBagel);
        Assertions.assertTrue(basket.addToBasket(onionBagel, 3));
    }

    @Test
    public void testAddValidProductBasketTooSmall()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);

        Assertions.assertNotNull(onionBagel);
        Assertions.assertFalse(basket.addToBasket(onionBagel, 3));
    }

    @Test
    public void testRemoveValidProduct()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);
        basket.addToBasket(onionBagel);

        Assertions.assertTrue(basket.removeFromBasket("BGLO"));
    }

    @Test
    public void testRemoveProductNotInBasket()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);
        basket.addToBasket(onionBagel);

        Assertions.assertFalse(basket.removeFromBasket("COFC"));
    }

    @Test
    public void testRemoveEmptyBasket()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);

        Assertions.assertFalse(basket.removeFromBasket("BGLO"));
    }

    @Test
    public void testRemoveProductShowsMessage()
    {
        System.setOut(new PrintStream(outputStreamCaptor));
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        basket.removeFromBasket("BGLO");
        String expectedString = "Product of SKU BGLO wasn't found in your basket!\n";

        Assertions.assertEquals(expectedString, outputStreamCaptor.toString());
        System.setOut(standardOut);
    }

    @Test
    public void testChangeBasketCapacitySuccess()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);

        Assertions.assertTrue(basket.changeSize(50));
        Assertions.assertTrue(basket.changeSize(100));
        Assertions.assertTrue(basket.changeSize(1));
    }

    @Test
    public void testChangeBasketCapacityInvalid()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);

        Assertions.assertFalse(basket.changeSize(-50));
        Assertions.assertFalse(basket.changeSize(-6));
        Assertions.assertFalse(basket.changeSize(-1000));
    }

    @Test
    public void testChangeBasketCapacityItemsAlreadyInBasketValid()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);

        Assertions.assertTrue(basket.changeSize(50));
        Assertions.assertTrue(basket.changeSize(100));
        Assertions.assertTrue(basket.changeSize(3));
    }

    @Test
    public void testChangeBasketCapacityItemsAlreadyInBasketNotValid()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);

        Assertions.assertFalse(basket.changeSize(0));
        Assertions.assertFalse(basket.changeSize(1));
        Assertions.assertFalse(basket.changeSize(2));
    }

    @Test
    public void testGetTotalCost()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);
        Product coffeeLatte = new Product("COFL", inventory);
        Product cheese = new Product("FILC", inventory);

        basket.addToBasket(onionBagel);
        basket.addToBasket(coffeeLatte);
        basket.addToBasket(cheese);

        Assertions.assertEquals(1.9, basket.getTotal());

        basket.addToBasket(onionBagel);
        Assertions.assertEquals(2.39, basket.getTotal());
    }

    @Test
    public void testGetTotalCostEmptyBasket()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);

        Assertions.assertEquals(0.0, basket.getTotal());
    }

    @Test
    public void testSeePrices()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);

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
        basket.showPrices();
        Assertions.assertEquals(expectedString, outputStreamCaptor.toString());
        System.setOut(standardOut);
    }

    @Test
    public void testSeePriceOfOneItem()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);

        String expectedString = """
                Prices:
                Latte Coffee - 1.29
                """;

        System.setOut(new PrintStream(outputStreamCaptor));
        basket.showPrices("COFL");
        Assertions.assertEquals(expectedString, outputStreamCaptor.toString());
        System.setOut(standardOut);
    }

    @Test
    public void testSeePriceOfMultipleItems()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);

        String expectedString = """
                Prices:
                Latte Coffee - 1.29
                Ham Filling - 0.12
                Everything Bagel - 0.49
                """;

        System.setOut(new PrintStream(outputStreamCaptor));
        basket.showPrices(new String[]{"COFL", "FILH", "BGLE"});
        Assertions.assertEquals(expectedString, outputStreamCaptor.toString());
        System.setOut(standardOut);
    }

    @Test
    public void testAddFillingsNoBagel()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);

        ByteArrayInputStream testIn = new ByteArrayInputStream(("y".getBytes()));
        System.setIn(testIn);
        Assertions.assertTrue(basket.addToBasket(new Product("FILH", inventory)));
    }

    @Test
    public void testAddToBasketOutOfStock()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);

        inventory.setStock("BGLO", 0);
        Assertions.assertFalse(basket.addToBasket(onionBagel));
    }

    @Test
    public void testSixBagelDiscount()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);

        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.getBagelDiscount();
        Assertions.assertEquals(2.49, basket.getTotal());
    }

    @Test
    public void testTwelveBagelDiscount()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);

        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        //  Change stock, tests ran out of Onion Bagel :(
        //  Then add two more :)
        basket.getBagelDiscount();

        Assertions.assertEquals(3.99, basket.getTotal());
    }

    @Test
    public void testCoffeeBagelDiscount()
    {
        Inventory inventory = new Inventory();
        Basket basket = new Basket(inventory);
        Product onionBagel = new Product("BGLO", inventory);
        Product blackCoffee = new Product("COFB", inventory);

        basket.addToBasket(onionBagel);
        basket.addToBasket(blackCoffee);

        Assertions.assertEquals(1.25, basket.getTotal());
    }
}
