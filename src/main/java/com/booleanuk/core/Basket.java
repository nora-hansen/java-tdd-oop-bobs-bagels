package com.booleanuk.core;

import java.util.ArrayList;

public class Basket {
    private ArrayList<Product> basket;
    private int size;

    public Basket()
    {
        this.basket = new ArrayList<>();
        this.size = 10;
    }

    public boolean addToBasket(Product product)
    {
        if(product != null)
        {
            if(basket.size() < size) {
                basket.add(product);
                System.out.println("1 " + product.getName() + " has been added to your basket!");
                return true;
            }   else {
                System.out.println("Could not add " + product.getName() + " to basket, your basket is full!");
                return false;
            }
        }
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
                    return true;
                }
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
            this.size = size;
            return true;
        }
        System.out.println("Could not change basket size: "
                + ((size < 0)
                ? "Size cannot be negative!"
                : "Basket already has more items in it than desired size!"));
        return false;

    }

    public boolean removeFromBasket(String sku)
    {
        for(Product item : basket)
        {
            if(item.getSku().equals(sku)) {
                basket.remove(item);
                System.out.println("Removed 1 " + item.name);
                return true;
            }
        }
        System.out.println("Product of SKU " + sku + " wasn't found in your basket!");
        return false;
    }
}
