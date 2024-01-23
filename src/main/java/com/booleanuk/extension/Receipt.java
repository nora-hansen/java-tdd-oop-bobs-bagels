package com.booleanuk.extension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Receipt {
    private final Basket basket;
    private final Inventory inventory;
    private final LocalDate date;
    private final LocalTime time;
    private final DateTimeFormatter format;
    private final String topText;
    private final String[] bottomText;
    private final HashMap<String, Integer> productCounts;
    private int width = 30;
    public Receipt(Basket basket, Inventory inventory)
    {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.format = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.basket = basket;
        this.inventory = inventory;
        this.topText = "~~~ Bob's Bagels ~~~";
        this.bottomText = new String[]{"Thank you", "for your order!"};
        this.productCounts = new HashMap<>();
    }

    /**
     * Generate the receipt string
     * @see Basket::getDiscountedItems()
     * @see Receipt::getPadding()
     * @see Receipt::getDateTime()
     * @see Receipt::getDashedLine()
     * @see Receipt::generateProductStrings()
     * @see Receipt::generateTotalString()
     * @return - Receipt string
     */
    public String generateReceipt()
    {
        basket.getTotal();
        countProducts();
        // Top text
        StringBuilder receipt = new StringBuilder(
                String.format("%" + getPadding(this.topText)
                        + "s%s%" + getPadding(this.topText)
                        + "s", "", this.topText, ""));
        // Date and time
        receipt.append("\n\n").
                append(String.format("%" + getPadding(getDateTime())
                        + "s%s%" + getPadding(getDateTime())
                        + "s", "", getDateTime(), ""));

        receipt.append("\n\n").append(getDashedLine());
        // Products
        receipt.append("\n\n");
        receipt.append(generateProductStrings());
        receipt.append("\n").append(getDashedLine());
        // Total
        receipt.append("\n");
        receipt.append(generateTotalString());
        // Show if discounted items exist
        if(!basket.getDiscountedItems().isEmpty())
        {
            String[] youSaved = {"You saved a total of £" + basket.getDiscount(), "on this shop"};
            receipt.append("\n");
            receipt.append(String.format("%" + getPadding(youSaved[0])
                            + "s%s%" + getPadding(youSaved[0])
                            + "s", "", youSaved[0], ""));
            receipt.append("\n")
                    .append(String.format("%" + getPadding(youSaved[1])
                    + "s%s%" + getPadding(youSaved[1])
                    + "s", "", youSaved[1], ""));
            receipt.append("\n");
        }
        // Bottom text
        receipt.append("\n").
                append(String.format("%" + getPadding(this.bottomText[0])
                        + "s%s%" + getPadding(this.bottomText[0])
                        + "s", "", this.bottomText[0], ""));
        receipt.append("\n").
                append(String.format("%"
                + getPadding(this.bottomText[1])
                        + "s%s%" + getPadding(this.bottomText[1])
                        + "s", "", this.bottomText[1], ""));

        System.out.println(receipt);

        return receipt.toString();
    }

    /**
     * Get padding size
     * @param s - String to pad
     * @return The amount of padding to add to each side
     */
    public int getPadding(String s)
    {
        return (width - s.length()) / 2;
    }

    /**
     * Generates dashes to form a line across receipt
     * @return Dashed line
     */
    public String getDashedLine()
    {
        // For self reference: Math.max(x, y) returns the bigger number
        return "-".repeat(Math.max(0, this.width));
    }

    /**
     * Gets a string of the date and time formatted like "YYYY-MM-DD  HH:mm:ss"
     * @return The date and time string
     */
    public String getDateTime()
    {
        return this.date + "  " + this.format.format(time);
    }

    /**
     * Count the products
     */
    public void countProducts()
    {
        for(Product p : this.basket.getBasket())
        {
            if(productCounts.containsKey(p.getSku()))
            {
                productCounts.put(p.getSku(), productCounts.get(p.getSku()) + 1);
            }   else {
                productCounts.put(p.getSku(), 1);
            }
        }
        //this.productCounts = basket.getProductCounts();
    }

    /**
     * Generates strings of the product name, amount, and price
     * @return The generated strings
     */
    public String generateProductStrings()
    {
        StringBuilder productString = new StringBuilder();

        for(String key : productCounts.keySet())    {
            String nameString = inventory.getVariant(key) + " " + inventory.getName(key);
            String numbersString = productCounts.get(key) + " £" + inventory.getPrice(key) * productCounts.get(key);
            productString.append(generateMidSpacedString(nameString, numbersString));
            if(!basket.getDiscountedItems().isEmpty() && basket.getDiscountedItems().containsKey(key))
            {
                double discount = basket.getDiscountedItems().get(key)[1];
                discount = Math.round(discount*100);
                discount = discount/100;
                String discountString = "(-£" + discount + ")";
                productString.append(String.format("%" + (width - discountString.length())
                                + "s%s", "", " " + discountString));
                productString.append("\n");
            }
        }

        return productString.toString();
    }

    /**
     * Generate a string for the total price at the bottom
     * @return The generated string
     */
    public String generateTotalString()
    {
        String total = "Total";
        String numbers = "£" + basket.getTotal();
        return generateMidSpacedString(total, numbers);
    }

    /**
     * Generate a string with the leftString on the far left, and rightString on the far Right
     * @param leftString - String to place on the far left
     * @param rightString - String to place on the far right
     * @return The generated string
     */
    public String generateMidSpacedString(String leftString, String rightString)
    {
        StringBuilder resultingString = new StringBuilder();
        int padding = (width - (leftString.length() + rightString.length()));
        resultingString.append(leftString);
        resultingString.append(" ".repeat(padding));
        resultingString.append(rightString);
        resultingString.append("\n");

        return resultingString.toString();
    }
}
