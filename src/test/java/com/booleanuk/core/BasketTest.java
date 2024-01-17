package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertTrue(basket.addToBasket(onionBagel));
    }

    @Test
    public void testAddInvalidProduct()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product invalidBagel = new Product("INVL", inventory);

        Assertions.assertFalse(basket.addToBasket(invalidBagel));
    }

    @Test
    public void testAddValidProductBasketFull()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
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
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);
        basket.addToBasket(onionBagel);

        Assertions.assertTrue(basket.removeFromBasket("BGLO"));
    }

    @Test
    public void testRemoveProductNotInBasket()
    {
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);
        basket.addToBasket(onionBagel);

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
        Basket basket = new Basket();
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);
        basket.addToBasket(onionBagel);

        Assertions.assertFalse(basket.changeSize(0));
        Assertions.assertFalse(basket.changeSize(1));
        Assertions.assertFalse(basket.changeSize(2));
    }
}
