package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasketTest {
    @Test
    public void testAddValidProduct()
    {
        Basket basket = new Basket();
        Product onionBagel = new Product("BGLO");

        Assertions.assertNotNull(onionBagel);
        Assertions.assertTrue(basket.addToBasket(onionBagel));
    }

    @Test
    public void testAddInvalidProduct()
    {
        Basket basket = new Basket();
        Product invalidBagel = new Product("INVL");

        Assertions.assertFalse(basket.addToBasket(invalidBagel));
    }

    @Test
    public void testAddValidProductBasketFull()
    {
        Basket basket = new Basket();
        Product onionBagel = new Product("BGLO");
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
}
