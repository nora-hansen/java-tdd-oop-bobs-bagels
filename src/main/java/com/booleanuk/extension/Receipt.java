package com.booleanuk.extension;

import java.time.LocalDate;
import java.time.LocalTime;

public class Receipt {
    Basket basket;
    private final LocalDate date;
    private final LocalTime time;
    private final String topText;
    private final String[] bottomText;
    public Receipt(Basket basket)
    {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.basket = basket;
        this.topText = "~~~ Bob's Bagels ~~~";
        this.bottomText = new String[]{"Thank you", "for your purchase!"};
    }

    /*
          ~~~ Bob's Bagels ~~~

          2024-01-19  10:31:50

        ------------------------

        Onion Bagel     5 £2.45

        ------------------------
        Total             £2.45

                Thank you
             for your order!
     */
    public String generateReceipt()
    {
        String testString = "~~~ Bob's Bagels ~~~";
        int width = 40;
        int padding = (width - testString.length()) / 2;
        String centeredText = String.format("%" + padding + "s%s%" + padding + "s", "", testString, "");
        System.out.println(centeredText);

        return centeredText;
    }
}
