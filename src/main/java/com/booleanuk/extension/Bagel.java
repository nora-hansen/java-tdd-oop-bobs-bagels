package com.booleanuk.extension;

import java.util.ArrayList;

public class Bagel extends Product {
    ArrayList<Filling> fillings;
    public Bagel(String sku, Inventory inv)
    {
        super(sku, inv);
        this.fillings = new ArrayList<>();
    }

    public void addFilling(Filling filling)
    {
        this.fillings.add(filling);
    }
}
