package com.booleanuk.extension;

import java.util.HashMap;

public class Inventory {
    private final HashMap<String, Double> prices;
    private final HashMap<String, Integer> stock;
    private final HashMap<String, String> names;
    private final HashMap<String, String> variants;

    public Inventory()
    {
        // The price of each item
        this.prices = new HashMap<>(){
            {
                put("BGLO", 0.49);
                put("BGLP", 0.39);
                put("BGLE", 0.49);
                put("BGLS", 0.49);
                put("COFB", 0.99);
                put("COFW", 1.19);
                put("COFC", 1.29);
                put("COFL", 1.29);
                put("FILB", 0.12);
                put("FILE", 0.12);
                put("FILC", 0.12);
                put("FILX", 0.12);
                put("FILS", 0.12);
                put("FILH", 0.12);
            }};
        // The amount of each left
        this.stock = new HashMap<>(){
            {
                put("BGLO", 50);
                put("BGLP", 50);
                put("BGLE", 50);
                put("BGLS", 50);
                put("COFB", 50);
                put("COFW", 50);
                put("COFC", 50);
                put("COFL", 50);
                put("FILB", 50);
                put("FILE", 50);
                put("FILC", 50);
                put("FILX", 50);
                put("FILS", 50);
                put("FILH", 50);
            }};
        // The name of each item
        this.names = new HashMap<>(){
            {
                put("BGLO", "Bagel");
                put("BGLP", "Bagel");
                put("BGLE", "Bagel");
                put("BGLS", "Bagel");
                put("COFB", "Coffee");
                put("COFW", "Coffee");
                put("COFC", "Coffee");
                put("COFL", "Coffee");
                put("FILB", "Filling");
                put("FILE", "Filling");
                put("FILC", "Filling");
                put("FILX", "Filling");
                put("FILS", "Filling");
                put("FILH", "Filling");
            }};
        // What variant the items are
        this.variants = new HashMap<>(){
            {
                put("BGLO", "Onion");
                put("BGLP", "Plain");
                put("BGLE", "Everything");
                put("BGLS", "Sesame");
                put("COFB", "Black");
                put("COFW", "White");
                put("COFC", "Cappuccino");
                put("COFL", "Latte");
                put("FILB", "Bacon");
                put("FILE", "Egg");
                put("FILC", "Cheese");
                put("FILX", "Cream Cheese");
                put("FILS", "Smoked Salmon");
                put("FILH", "Ham");
            }};
    }

    /**
     * Checks the map of names to find the correct value
     * @param sku
     * @return
     */
    public String getName(String sku)
    {
        if(this.names.get(sku) != null)
        {
            return this.names.get(sku);
        }
        return "";
    }

    /**
     * Checks the map of variants to find the correct value
     * @param sku
     * @return
     */
    public String getVariant(String sku)
    {
        if(this.variants.get(sku) != null)
        {
            return this.variants.get(sku);
        }
        return "";
    }

    public HashMap<String, String> getVariants()
    {
        return this.variants;
    }

    /**
     * Checks the map of prices to find the correct value
     * @param sku
     * @return
     */
    public Double getPrice(String sku)
    {
        if(this.prices.get(sku) != null)
        {
            return this.prices.get(sku);
        }
        return -1.0;
    }

    /**
     * Checks the map of stack to find the correct value
     * @param sku
     * @return
     */
    public int getStock(String sku)
    {
        if(this.stock.get(sku) != null)
        {
            return this.stock.get(sku);
        }
        return -1;
    }

    /**
     * Sets the amount items left for a specific product
     * @param sku
     * @param amount
     */
    public void setStock(String sku, int amount)
    {
        if(this.stock.get(sku) != null)
        {
            this.stock.put(sku, amount);
        }
    }

    /**
     * Generates a string of the name and variant for a product
     * @param sku
     * @return
     */
    public String getProductString(String sku)
    {
        return getVariant(sku) + " " +
                getName(sku) +
                " - " + getPrice(sku);
    }

}