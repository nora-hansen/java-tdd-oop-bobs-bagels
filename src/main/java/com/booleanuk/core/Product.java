package com.booleanuk.core;

public class Product {
    Inventory inventory;
    String name;
    Double price;
    String sku;
    String variant;

    public Product (String sku, Inventory inv)
    {
        this.inventory = inv;
        this.sku = sku;
        this.price = this.inventory.getPrice(sku);
        this.name = this.inventory.getName(sku);
        this.variant = this.inventory.getVariant(sku);
    }

    public String getName()
    {
        return this.name;
    }

    public double getPrice()
    {
        return this.price;
    }

    public String getSku()
    {
        return this.sku;
    }
}
