package com.booleanuk.extension;

import java.util.Scanner;

public class BobsBagels {
    private final String mainMenu;
    private final String managerMenu;
    private final String shopMenu;
    Scanner sc;
    Inventory inventory;
    Basket basket;

    /*
    Not implemented
     */
    public BobsBagels()
    {
        this.mainMenu = """
                - Welcome to Bob's Bagels! What will it be? -
                    (S)hop
                    (M)anager actions
                    (Q)uit
                Input: """;
        this.managerMenu = """
                Manager actions:
                    (C)hange basket size
                    (R)estock
                    (B)ack to main menu
                    (Q)uit
                Input: """;
        this.shopMenu = """
                Shop actions:
                    (L)ist all products
                    (A)dd product to basket
                    (R)emove product from basket
                    (S)ee total
                    (E)mpty basket
                    (P)ay
                    (B)ack
                    (Q)uit
                Input: """;
        this.sc = new Scanner(System.in);
    }

    public void showMenu()
    {
        System.out.print(this.mainMenu);
    }

    public void showManagerMenu()
    {
        System.out.print(this.managerMenu);
    }

    public void showShopMenu()
    {
        System.out.print(this.shopMenu);
    }
}
