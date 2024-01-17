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
}
