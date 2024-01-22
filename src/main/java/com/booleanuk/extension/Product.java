package com.booleanuk.extension;

public interface Product {
    String name = null;
    Double price = null;
    String sku = null;
    String variant = null;

    public String getName();

    public double getPrice();

    public String getSku();

    public String getVariant();
}
