package com.booleanuk.extension;

import java.util.ArrayList;
import java.util.HashMap;

public class Basket {
    Inventory inv;    // To get information about products
    private final ArrayList<Product> basket;
    private final HashMap<String, Integer> productCounts;   // How many of each product is in the basket
    private final HashMap<String, Double[]> discountedItems;    // The discounted items, their original price,
    //  discount, and discounted price
    private int size;
    private double total;
    private double discount;

    public Basket(Inventory inventory)
    {
        this.inv = inventory;
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
            if(!product.getName().equals("Filling"))
                return addBagelCoffee(product);
            else
                return addFilling((Filling) product);
        }
        // Will change this to maybe exceptions? Maybe.
        System.out.println("Could not add product to basket, because product is null");
        return false;
    }

    public boolean addBagelCoffee(Product product)
    {
        if(basket.size() < size) {  // Is basket full?
            basket.add(product);
            this.total += product.getPrice();   // Add cost to total
            inv.setStock(product.getSku(), inv.getStock(product.getSku()) -1);
            addToCount(product.getSku());

            System.out.println("1 " + product.getVariant()
                    + " " + product.getName()
                    + " has been added to your basket!");
            return true;

        }   else {
            System.out.println("Could not add " + product.getVariant()
                    + " " + product.getName()
                    + " to basket, your basket is full!");
            return false;
        }
    }

    /**
     * Add filling to last bagel
     * @param filling - The filling object to add
     * @return True/False if the action was successful
     */
    public boolean addFilling(Filling filling)
    {
        if(basket.size() < size) {  // Is basket full?
            for(int i = basket.size()-1; i > 0; i--)
            {
                if(basket.get(i) instanceof Bagel)
                {
                    System.out.println("I ran");
                    ((Bagel) basket.get(i)).addFilling(filling);
                    inv.setStock(filling.getSku(), inv.getStock(filling.getSku()) -1);
                    addToCount(filling.getSku());
                    return true;
                }
                System.out.println("Should not run");
            }
            System.out.println("No bagels are in basket, filling added anyway");
            addBagelCoffee(filling);
            return true;
        }   else {
            System.out.println("Could not add "
                    + filling.getVariant() + " "
                    + filling.getName() + " to basket, your basket is full!");
            return false;
        }
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
        if(amount > (size-(this.basket.size())))
        {
            System.out.println("Insufficient basket space");
            return false;
        }
        for(int i = 0; i < amount; i++)
        {
            result = addToBasket(product);
        }
        return result;
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
        return this.inv.getStock(sku) > 0;
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
     * Get the discount for 12 bagels and/or 6 bagels
     */
    public void getBagelDiscount()
    {
        Double[] priceDiscountNewPrice = new Double[] {0.0,0.0,0.0};    // {Original price,
        // (original price - discounted price),
        // discounted price}
        for(String sku : productCounts.keySet())
        {
            // Eligible for 12 item discount
            while(productCounts.get(sku) >= 12)
            {
                priceDiscountNewPrice[0] = this.inv.getPrice(sku) * 12.0;
                priceDiscountNewPrice[2] = 3.99d;
                priceDiscountNewPrice[1] += priceDiscountNewPrice[0] - priceDiscountNewPrice[2];    // Find difference
                productCounts.put(sku, productCounts.get(sku) - 12);
                discountedItems.put(sku, priceDiscountNewPrice);
            }
            // Eligible for 6 item discount
            while(productCounts.get(sku) >= 6)
            {
                priceDiscountNewPrice[0] = this.inv.getPrice(sku) * 6.0;
                // Different discount for Plain and others
                priceDiscountNewPrice[2] = inv.getVariant(sku).equals("Plain") ? 2.29d : 2.49d;
                priceDiscountNewPrice[1] += priceDiscountNewPrice[0] - priceDiscountNewPrice[2];
                productCounts.put(sku, productCounts.get(sku) - 6);
                discountedItems.put(sku, priceDiscountNewPrice);
            }
        }
        this.discount += priceDiscountNewPrice[1];
    }

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
                if (inv.getVariant(sku).equals("Black"))
                    coffees++;
                else if (inv.getName(sku).equals("Bagel")) {
                    if (inv.getVariant(sku).equals("Plain"))
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
        if(priceDiscountNewPrice[1] > 0.0) this.discountedItems.put("COFB", priceDiscountNewPrice);
        this.discount += priceDiscountNewPrice[1];
    }

    /**
     * Get the total discount as a double rounded to 2 decimals
     * @return The calculated discount
     */
    public double getDiscount()
    {
        this.discount = Math.round(this.discount*100);
        this.discount = this.discount/100;
        return this.discount;
    }

    /**
     * Getter for the discounted items
     * @return - discountedItems
     */
    public HashMap<String, Double[]> getDiscountedItems() {
        return this.discountedItems;
    }

    /**
     * Getter for the productCounts
     * @return - productCounts
     */
    public HashMap<String, Integer> getProductCounts()
    {
        return this.productCounts;
    }

    /**
     * The total cost as a double rounded to two decimals
     * @see Basket::calculateTotal()
     * @return - The total cost
     */
    public double getTotal()
    {
        calculateTotal();
        this.total = Math.round(this.total*100);
        this.total = this.total/100;
        return this.total;
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
                this.inv.getProductString("BGLO") + "\n" +
                this.inv.getProductString("BGLP") + "\n" +
                this.inv.getProductString("BGLE") + "\n" +
                this.inv.getProductString("BGLS") + "\n" +
                this.inv.getProductString("COFB") + "\n" +
                this.inv.getProductString("COFW") + "\n" +
                this.inv.getProductString("COFC") + "\n" +
                this.inv.getProductString("COFL") + "\n" +
                this.inv.getProductString("FILB") + "\n" +
                this.inv.getProductString("FILE") + "\n" +
                this.inv.getProductString("FILC") + "\n" +
                this.inv.getProductString("FILX") + "\n" +
                this.inv.getProductString("FILS") + "\n" +
                this.inv.getProductString("FILH") + "\n";

        System.out.print(prices);
    }

    /**
     * Show the price of one product
     * @see Inventory::getProductString(...)
     * @param product - Product to show price of
     */
    public void showPrices(String product)
    {
        String prices = "Prices:\n" +
                this.inv.getProductString(product) + "\n";

        System.out.print(prices);
    }

    /**
     * Show the price of multiple products
     * @see Inventory::getProductString(...)
     * @param products - Products to show price of
     */
    public void showPrices(String[] products)
    {
        StringBuilder prices = new StringBuilder();

        prices.append("Prices:\n");
        for(String s : products) {
            prices.append(this.inv.getProductString(s)).append("\n");
        }

        System.out.print(prices);
    }
}