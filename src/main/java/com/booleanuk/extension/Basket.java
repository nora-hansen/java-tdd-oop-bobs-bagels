package com.booleanuk.extension;

import java.util.ArrayList;
import java.util.HashMap;

public class Basket {
    Inventory inventory;
    private final ArrayList<Product> basket;
    private final HashMap<String, Integer> productCounts;
    private final HashMap<String, Double[]> discountedItems;
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

    /**
     * Adds a Product object to the basket
     * @param product - The product to be added to the basket
     * @return True/False Indicating if addition was successful or not
     */
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
                this.inventory.setStock(product.getSku(), this.inventory.getStock(product.getSku()) -1);

                System.out.println("1 " + product.getVariant()
                        + " " + product.getName()
                        + " has been added to your basket!");
                return true;
            }   else {
                System.out.println("Could not add "
                        + product.getVariant() + " "
                        + product.getName() + " to basket, your basket is full!");
                return false;
            }
        }
        // Will change this to maybe exceptions? Maybe.
        System.out.println("Could not add product to basket, because product is null");
        return false;
    }

    /**
     * Adds a product to the basket a number of times
     * @see Basket::addToBasket(...)
     * @param product - Product to be added to basket
     * @param amount - Amount of products to add
     * @return True/False indicating if addition was successful or not
     */
    public boolean addToBasket(Product product, int amount)
    {
        boolean result = false;
        for(int i = 0; i < amount; i++)
        {
            result = addToBasket(product);
        }
        return result;
    }

    /**
     * Change the maximum size of the basket
     * @param size - Desired size
     * @return True/False if action was successful.
     *           If current basket is smaller than desired size, returns false
     */
    public boolean changeSize(int size)
    {
        // Checks if wanted size is valid
        if(size >= 0 && size >= basket.size())
        {
            System.out.println("Basket size is now " + size);
            this.size = size;
            return true;
        }
        System.out.println("Could not change basket size: "
                + ((size < 0)
                ? "Size cannot be negative!"    // Displayed if size is negative
                : "Basket already has more items in it than desired size!"));   // Displayed if size is less than
                                                                                //   basket is filled
        return false;
    }

    /**
     * Check if there are items in stock for specified item
     * @see Inventory::getStock(...)
     * @param sku - SKU of item to check stock for
     * @return  True is there are items left, false if not
     */
    public boolean checkStock(String sku)
    {
        return this.inventory.getStock(sku) > 0;
    }

    /**
     * Basket getter
     * @return basket
     */
    public ArrayList<Product> getBasket()
    {
        return this.basket;
    }

    /**
     * Calculate the total cost of items in the basket
     * @see Product::getPrice()
     * @see Basket::getBagelDiscount()
     * @see Basket::getCoffeeBagelDiscount()
     */
    public void calculateTotal()
    {
        this.total = 0; // reset total in case calculations are already done
        for(Product p : this.basket)
        {
            this.total += p.getPrice();
        }
        getBagelDiscount(); // 6 or 12 bagel discount
        getCoffeeBagelDiscount();   // Coffee & Bagel discount
        this.total -= this.discount;
    }

    public double getTotal()
    {
        calculateTotal();
        this.total = Math.round(this.total*100);
        this.total = this.total/100;
        return this.total;
    }

    /**
     * Add a product to a count
     * @param sku - Product to be counted
     */
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
         Add to receipt
     */

    /**
     * Get the discount for 12 bagels and/or 6 bagels
     */
    public void getBagelDiscount()
    {
        Double[] priceDiscountNewPrice = new Double[] {0.0,0.0,0.0};

        for(String sku : productCounts.keySet())
        {
            // Eligible for 12 item discount
            while(productCounts.get(sku) >= 12)
            {
                priceDiscountNewPrice[0] = this.inventory.getPrice(sku) * 12.0;
                priceDiscountNewPrice[2] = 3.99d;
                priceDiscountNewPrice[1] += priceDiscountNewPrice[0] - priceDiscountNewPrice[2];    // Find difference
                productCounts.put(sku, productCounts.get(sku) - 12);
                discountedItems.put(sku, priceDiscountNewPrice);
            }
            // Eligible for 6 item discount
            while(productCounts.get(sku) >= 6)
            {
                priceDiscountNewPrice[0] = this.inventory.getPrice(sku) * 6.0;
                                                                        // Different discount for Plain and others
                priceDiscountNewPrice[2] = inventory.getVariant(sku).equals("Plain") ? 2.29d : 2.49d;
                priceDiscountNewPrice[1] += priceDiscountNewPrice[0] - priceDiscountNewPrice[2];
                productCounts.put(sku, productCounts.get(sku) - 6);
                discountedItems.put(sku, priceDiscountNewPrice);
            }
        }
        this.discount += priceDiscountNewPrice[1];
    }

    /*
        TODO
         Other coffee types? Maybe not, too great a deal. Bob is greedy
     */

    /**
     * Get Coffee & Bagel discount
     */
    public void getCoffeeBagelDiscount()
    {
        Double[] priceDiscountNewPrice = new Double[] {0.0,0.0,0.0};

        int coffees = 0, bagels = 0, plains = 0;

        for(String sku : productCounts.keySet())
        {
            while(productCounts.get(sku) > 0) {
                // Eligible for 12 item discount
                if (inventory.getVariant(sku).equals("Black"))
                    coffees++;
                else if (inventory.getName(sku).equals("Bagel")) {
                    if (inventory.getVariant(sku).equals("Plain"))
                        plains++;
                    else
                        bagels++;
                }
                productCounts.put(sku, productCounts.get(sku) - 1);
            }
        }
        while(coffees >0 && bagels >0)
        {
            priceDiscountNewPrice[0] = 0.99 + 0.49;
            priceDiscountNewPrice[2] = 1.25;
            priceDiscountNewPrice[1] += priceDiscountNewPrice[0] - priceDiscountNewPrice[2];
            coffees--;
            bagels--;
        }
        while(coffees >0 && plains >0)
        {
            priceDiscountNewPrice[0] = 0.99 + 0.39;
            priceDiscountNewPrice[2] = 1.25;
            priceDiscountNewPrice[1] += priceDiscountNewPrice[0] - priceDiscountNewPrice[2];
            coffees--;
            plains--;
        }
        discountedItems.put("COFB", priceDiscountNewPrice);
        this.discount += priceDiscountNewPrice[1];
    }

    public double getDiscount()
    {
        this.discount = Math.round(this.discount*100);
        this.discount = this.discount/100;
        return this.discount;
    }

    public HashMap<String, Double[]> getDiscountedItems() {
        return this.discountedItems;
    }

    /**
     * Remove a product from the basket
     * @see Product::getSku()
     * @param sku - SKU of product to remove
     * @return  True/False indicating if the product could be removed
     */
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

    /**
     * Show the product prices
     */
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

    /**
     * Show the price of one product
     * @param product - Product to show price of
     */
    public void showPrices(String product)
    {
        String prices = "Prices:\n" +
                this.inventory.getProductString(product) + "\n";

        System.out.print(prices);
    }

    /**
     * Show the price of multiple products
     * @param products - Products to show price of
     */
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
