package com.booleanuk.extension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Receipt {
    Basket basket;
    Inventory inventory;
    private final LocalDate date;
    private final LocalTime time;
    private final DateTimeFormatter format;
    private final String topText;
    private final String[] bottomText;
    private final HashMap<String, Integer> productCounts;
    int width = 30;
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
        countProducts();
    }

    public String generateReceipt()
    {
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
        // Bottom text
        receipt.append("\n\n").
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

    public int getPadding(String s)
    {
        return (width - s.length()) / 2;
    }

    public String getDashedLine()
    {
                          // For self reference: Math.max(x, y) returns the bigger number
        return "-".repeat(Math.max(0, this.width));
    }

    public String getDateTime()
    {
        return this.date + "  " + this.format.format(time);
    }

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
    }

    public String generateProductStrings()
    {
        StringBuilder productString = new StringBuilder();

        for(String key : productCounts.keySet())    {
            String nameString = inventory.getVariant(key) + " " + inventory.getName(key);
            String numbersString = productCounts.get(key) + " " + inventory.getPrice(key) * productCounts.get(key);

            productString.append(generateMidSpacedString(nameString, numbersString));
        }


        return productString.toString();
    }

    public String generateTotalString()
    {
        String total = "Total";
        String numbers = "" + basket.getTotal();
        return generateMidSpacedString(total, numbers);
    }

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
