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

    public boolean addToBasket(Inventory inv, Product product)
    {
        // Check if product stock is more than zero
        if(!this.checkStock(inv, product.getSku())) {
            System.out.println("Product is out of stock");
            return false;
        }
        // Check if the product name is valid
        if(!product.getName().isEmpty())
        {
            if(basket.size() < size) {  // Is basket full?
                basket.add(product);
                this.total += product.getPrice();   // Add cost to total

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
                }
                System.out.println(amount + " " + product.getName() + "s has been added to your basket!");
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

    public boolean checkStock(Inventory inv, String sku)
    {
        return inv.getStock(sku) > 0;
    }

    public ArrayList<Product> getBasket()
    {
        return this.basket;
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
        String prices = "Prices:\n" +
                inv.getProductString("BGLO") + "\n" +
                inv.getProductString("BGLP") + "\n" +
                inv.getProductString("BGLE") + "\n" +
                inv.getProductString("BGLS") + "\n" +
                inv.getProductString("COFB") + "\n" +
                inv.getProductString("COFW") + "\n" +
                inv.getProductString("COFC") + "\n" +
                inv.getProductString("COFL") + "\n" +
                inv.getProductString("FILB") + "\n" +
                inv.getProductString("FILE") + "\n" +
                inv.getProductString("FILC") + "\n" +
                inv.getProductString("FILX") + "\n" +
                inv.getProductString("FILS") + "\n" +
                inv.getProductString("FILH") + "\n";

        System.out.print(prices);
    }

    public void showPrices(String product, Inventory inv)
    {
        String prices = "Prices:\n" +
                inv.getProductString(product) + "\n";

        System.out.print(prices);
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
