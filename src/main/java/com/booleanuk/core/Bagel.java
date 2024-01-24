package com.booleanuk.core;

import java.util.ArrayList;

public class Bagel extends Product {
    ArrayList<Filling> fillings;
    public Bagel(String sku, Inventory inv)
    {
        super(sku, inv);
    }

    public void addFilling(Filling filling)
    {
        this.fillings.add(filling);
    }

}
