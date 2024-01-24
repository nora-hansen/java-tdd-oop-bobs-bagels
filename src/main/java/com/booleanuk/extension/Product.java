package com.booleanuk.extension;


public class Product {
    final String name;
    final Double price;
    final String sku;
    final String variant;

    public Product (String sku, Inventory inv)
    {
        this.sku = sku;
        this.price = inv.getPrice(sku);
        this.name = inv.getName(sku);
        this.variant = inv.getVariant(sku);
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

    public String getVariant()
    {
        return this.variant;
    }
}
