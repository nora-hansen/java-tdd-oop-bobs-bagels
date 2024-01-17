package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasketTest {
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
        Inventory inventory = new Inventory();

        Assertions.assertFalse(basket.removeFromBasket("BGLO"));
    }
}
