package com.booleanuk.extension;

import java.util.ArrayList;
import java.util.HashMap;

public class Basket {
    Inventory inventory;
    private final ArrayList<Product> basket;
    private final HashMap<String, Double[]> discountedItems;
    private final HashMap<String, Integer> productCounts;
    private int size;
    private double total;
    private double discount;

    public Basket(Inventory inventory)
    {
        this.inventory = inventory;
        this.basket = new ArrayList<>();
        this.discountedItems = new HashMap<>();
        this.productCounts = new HashMap<>();
        this.size = 50;
        this.total = 0.0;
        this.discount = 0.0;
    }

    public boolean addToBasket(Product product)
    {
        // Check if product stock is more than zero
        if(!this.checkStock(product.getSku())) {
            System.out.println("Product is out of stock");
            return false;
        }
        // Check if the product name is valid
        if(!product.getName().isEmpty())
        {
            if(basket.size() < size) {  // Is basket full?
                basket.add(product);
                addToCount(product.getSku());
                System.out.println("Added to count, " +  this.productCounts.get(product.getSku()));
                this.inventory.setStock(product.getSku(), this.inventory.getStock(product.getSku()) -1);

                System.out.println("1 " + product.getVariant() + " " + product.getName() + " has been added to your basket!");
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
        if(!this.checkStock(product.getSku())) {
            System.out.println("Product is out of stock");
            return false;
        }
        if(!product.getName().isEmpty())
        {
            if(basket.size() < size - amount) {
                for(int i = 0; i < amount; i++)
                {
                    basket.add(product);
                    addToCount(product.getSku());
                    this.inventory.setStock(product.getSku(), this.inventory.getStock(product.getSku()) -1);
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

    public boolean checkStock(String sku)
    {
        return this.inventory.getStock(sku) > 0;
    }

    public ArrayList<Product> getBasket()
    {
        return this.basket;
    }

    public void calculateTotal()
    {
        this.total = 0;
        for(Product p : this.basket)
        {
            this.total += p.getPrice();
        }
        getBagelDiscount();
        this.total -= this.discount;
    }

    public double getTotal()
    {
        calculateTotal();
        this.total = Math.round(this.total*100);
        this.total = this.total/100;
        return this.total;
    }

    public void addToCount(String sku)
    {
        if(this.productCounts.containsKey(sku))
        {
            productCounts.put(sku, productCounts.get(sku) + 1);
        }   else {
            productCounts.put(sku, 1);
        }
    }

    /*
    TODO
         Apply discount to total
         Add to receipt
         Take Plain Bagel into account
         Coffee + Bagel discount (how do they combine?)
     */
    public double getBagelDiscount()
    {
        Double[] priceDiscountNewPrice = new Double[] {0.0,0.0,0.0};

        for(String sku : productCounts.keySet())
        {
            // Eligible for 12 item discount
            while(productCounts.get(sku) >= 12)
            {
                System.out.println("12: " + productCounts.put(sku, productCounts.get(sku)));
                priceDiscountNewPrice[0] = this.inventory.getPrice(sku) * 12.0;
                priceDiscountNewPrice[2] = 3.99d;
                priceDiscountNewPrice[1] += priceDiscountNewPrice[0] - priceDiscountNewPrice[2];
                productCounts.put(sku, productCounts.get(sku) - 12);
            }
            // Eligible for 6 item discount
            while(productCounts.get(sku) >= 6)
            {
                priceDiscountNewPrice[0] = this.inventory.getPrice(sku) * 6.0;
                priceDiscountNewPrice[2] = 2.49d;
                priceDiscountNewPrice[1] += priceDiscountNewPrice[0] - priceDiscountNewPrice[2];
                productCounts.put(sku, productCounts.get(sku) - 6);
            }
            if(priceDiscountNewPrice[2] > 0)
            {
                discountedItems.put(sku, priceDiscountNewPrice);
            }
        }
        this.discount = priceDiscountNewPrice[1];
        return discount;
    }

    public double getCoffeeBagelDiscount()
    {

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

    public void showPrices()
    {
        String prices = "Prices:\n" +
                this.inventory.getProductString("BGLO") + "\n" +
                this.inventory.getProductString("BGLP") + "\n" +
                this.inventory.getProductString("BGLE") + "\n" +
                this.inventory.getProductString("BGLS") + "\n" +
                this.inventory.getProductString("COFB") + "\n" +
                this.inventory.getProductString("COFW") + "\n" +
                this.inventory.getProductString("COFC") + "\n" +
                this.inventory.getProductString("COFL") + "\n" +
                this.inventory.getProductString("FILB") + "\n" +
                this.inventory.getProductString("FILE") + "\n" +
                this.inventory.getProductString("FILC") + "\n" +
                this.inventory.getProductString("FILX") + "\n" +
                this.inventory.getProductString("FILS") + "\n" +
                this.inventory.getProductString("FILH") + "\n";

        System.out.print(prices);
    }

    public void showPrices(String product)
    {
        String prices = "Prices:\n" +
                this.inventory.getProductString(product) + "\n";

        System.out.print(prices);
    }

    public void showPrices(String[] products)
    {
        StringBuilder prices = new StringBuilder();

        prices.append("Prices:\n");
        for(String s : products) {
            prices.append(this.inventory.getProductString(s)).append("\n");
        }

        System.out.print(prices);
    }
}
