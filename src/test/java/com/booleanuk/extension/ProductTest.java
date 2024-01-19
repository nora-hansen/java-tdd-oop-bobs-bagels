package com.booleanuk.extension;

import com.booleanuk.core.Inventory;
import com.booleanuk.core.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    public void testGetNameOnionBagel()
    {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);

        Assertions.assertEquals("Bagel", onionBagel.getName());
    }

    @Test
    public void testGetNameInvalid()
    {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("INVL", inventory);

        Assertions.assertEquals("", onionBagel.getName());
    }

    @Test
    public void testGetPriceOnionBagel()
    {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Product onionBagel = new com.booleanuk.core.Product("BGLO", inventory);

        Assertions.assertEquals(0.49, onionBagel.getPrice());
    }

    @Test
    public void testGetPriceInvalid()
    {
        com.booleanuk.core.Inventory inventory = new Inventory();
        com.booleanuk.core.Product onionBagel = new Product("INVL", inventory);

        Assertions.assertEquals(-1.0, onionBagel.getPrice());
    }
}
