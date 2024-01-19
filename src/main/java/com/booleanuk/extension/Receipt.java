package com.booleanuk.extension;

import java.time.LocalDate;
import java.time.LocalTime;

public class Receipt {
    Basket basket;
    LocalDate date;
    LocalTime time;
    public Receipt(Basket basket)
    {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.basket = basket;
    }

    public String generateReceipt()
    {

    }
}
