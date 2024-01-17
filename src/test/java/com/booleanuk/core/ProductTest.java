package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    public void testGetNameOnionBagel()
    {
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("BGLO", inventory);

        Assertions.assertEquals("Onion", onionBagel.getName());
    }

    @Test
    public void testGetNameInvalid()
    {
        Inventory inventory = new Inventory();
        Product onionBagel = new Product("INVL", inventory);

        Assertions.assertEquals("", onionBagel.getName());
    }
}
