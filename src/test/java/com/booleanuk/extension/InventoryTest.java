package com.booleanuk.extension;

import com.booleanuk.core.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InventoryTest {
    @Test
    public void testGetName()
    {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();

        Assertions.assertEquals("Bagel", inventory.getName("BGLP"));
        Assertions.assertEquals("Bagel", inventory.getName("BGLO"));
        Assertions.assertEquals("Coffee", inventory.getName("COFC"));
        Assertions.assertEquals("Coffee", inventory.getName("COFL"));
        Assertions.assertEquals("Filling", inventory.getName("FILC"));
        Assertions.assertEquals("Filling", inventory.getName("FILH"));
        Assertions.assertEquals("", inventory.getName("INVL"));
    }

    @Test
    public void testGetProductString()
    {
        com.booleanuk.core.Inventory inventory = new Inventory();

        String expectedString = "Onion Bagel - 0.49";

        Assertions.assertEquals(expectedString, inventory.getProductString("BGLO"));
    }
}
