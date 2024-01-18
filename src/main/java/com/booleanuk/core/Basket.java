package com.booleanuk.core;

import java.util.ArrayList;

public class Basket {
    private final ArrayList<Product> basket;
    private int size;
    private double total;

    public Basket()
    {
        this.basket = new ArrayList<>();
        this.size = 10;
        this.total = 0.0;
    }

    public boolean addToBasket(Product product)
    {
        if(product != null && !product.getName().isEmpty())
        {
            if(basket.size() < size) {
                basket.add(product);
                this.total += product.getPrice();
                System.out.println("1 " + product.getVariant() + " " + product.getName() + " has been added to your basket!");
                return true;
            }   else {
                System.out.println("Could not add " + product.getVariant() + " " + product.getName() + " to basket, your basket is full!");
                return false;
            }
        }
        // Will change this to maybe exceptions? Maybe.
        System.out.println("Could not add product to basket, because product is null");
        return false;
    }

    public boolean addToBasket(Product product, int amount)
    {
        if(product != null)
        {
            if(basket.size() < size - amount) {
                for(int i = 0; i < amount; i++)
                {
                    basket.add(product);
                    System.out.println("1 " + product.getName() + " has been added to your basket!");
                }
                return true;
            }   else {
                System.out.println("Could not add " + product.getName() + " to basket, your basket is full!");
                return false;
            }
        }
        System.out.println("Could not add product to basket, because product is null");
        return false;
    }

    public boolean changeSize(int size)
    {
        if(size >= 0 && size >= basket.size())
        {
            System.out.println("Basket size is now " + size);
            this.size = size;
            return true;
        }
        System.out.println("Could not change basket size: "
                + ((size < 0)
                ? "Size cannot be negative!"
                : "Basket already has more items in it than desired size!"));
        return false;

    }

    public double getTotal()
    {
        this.total = Math.round(this.total*100);
        this.total = this.total/100;
        return this.total;
    }

    public boolean removeFromBasket(String sku)
    {
        for(Product item : basket)
        {
            if(item.getSku().equals(sku)) {
                basket.remove(item);
                System.out.println("Removed 1 " + item.getVariant() + " " + item.getName());
                return true;
            }
        }
        System.out.print("Product of SKU " + sku + " wasn't found in your basket!\n");
        return false;
    }

    public void showPrices(Inventory inv)
    {
        StringBuilder prices = new StringBuilder();

        prices.append("Prices:\n");
        prices.append(inv.getProductString("BGLO")).append("\n");
        prices.append(inv.getProductString("BGLP")).append("\n");
        prices.append(inv.getProductString("BGLE")).append("\n");
        prices.append(inv.getProductString("BGLS")).append("\n");
        prices.append(inv.getProductString("COFB")).append("\n");
        prices.append(inv.getProductString("COFW")).append("\n");
        prices.append(inv.getProductString("COFC")).append("\n");
        prices.append(inv.getProductString("COFL")).append("\n");
        prices.append(inv.getProductString("FILB")).append("\n");
        prices.append(inv.getProductString("FILE")).append("\n");
        prices.append(inv.getProductString("FILC")).append("\n");
        prices.append(inv.getProductString("FILX")).append("\n");
        prices.append(inv.getProductString("FILS")).append("\n");
        prices.append(inv.getProductString("FILH")).append("\n");

        System.out.print(prices.toString());
    }

    public void showPrices(String product, Inventory inv)
    {
        StringBuilder prices = new StringBuilder();

        prices.append("Prices:\n");
        prices.append(inv.getProductString(product)).append("\n");

        System.out.print(prices.toString());
    }

    public void showPrices(String[] products, Inventory inv)
    {
        StringBuilder prices = new StringBuilder();

        prices.append("Prices:\n");
        for(String s : products) {
            prices.append(inv.getProductString(s)).append("\n");
        }

        System.out.print(prices.toString());
    }
}
