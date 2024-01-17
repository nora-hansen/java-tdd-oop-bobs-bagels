package com.booleanuk.core;

public class Product {
    Inventory inventory;
    String name;
    Double price;
    String sku;
    String type;

    public Product (String sku, Inventory inv)
    {
        this.inventory = inv;
        this.sku = sku;
        this.price = this.inventory.getPrice(sku);
        this.name = this.inventory.getName(sku);
        this.type = this.inventory.getType(sku);
    }

    public String getName()
    {
        return this.name;
    }
}
