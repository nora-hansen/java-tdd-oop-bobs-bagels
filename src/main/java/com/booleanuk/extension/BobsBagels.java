package com.booleanuk.extension;

import java.util.Scanner;

public class BobsBagels {
    private final String mainMenu;
    private final String managerMenu;
    private final String shopMenu;
    Scanner sc;
    Inventory inventory;
    Basket basket;

    public BobsBagels()
    {
        this.mainMenu = """
                - Welcome to Bob's Bagels! What will it be? -
                    (S)hop
                    (M)anager actions
                    (Q)uit
                Input:\s""";
        this.managerMenu = """
                Manager actions:
                    (C)hange basket size
                    (R)estock
                    (B)ack to main menu
                    (Q)uit
                Input:\s""";
        this.shopMenu = """
                Shop actions:
                    (L)ist all products
                    (A)dd product to basket
                    (R)emove product from basket
                    (S)ee total
                    (E)mpty basket
                    (P)ay
                    (B)ack to main menu
                    (Q)uit
                Input:\s""";
        this.sc = new Scanner(System.in);
    }

    public void run()
    {
        menu();
    }

    public void menu()
    {
        showMenu();
        String choice = sc.nextLine();
        while(!choice.equalsIgnoreCase("Q"))
        {
            sc = new Scanner(System.in);
            switch(choice.toUpperCase())
            {
                case "S": shop(); break;
                case "M": manager(); break;
                default: showMenu(); break;
            }
            choice = sc.nextLine();
        }
    }

    public void shop()
    {
        showShopMenu();
        String choice = sc.nextLine();
        while(!choice.equalsIgnoreCase("B"))
        {
            sc = new Scanner(System.in);
            switch(choice.toUpperCase())
            {
                case "L": System.out.println("l"); break;
                case "A": System.out.println("a"); break;
                case "R": System.out.println("r"); break;
                case "S": System.out.println("s");break;
                default: showMenu(); break;
            }
            choice = sc.nextLine();
        }
    }

    public void manager()
    {
        showManagerMenu();
        String choice = sc.nextLine();
        while(!choice.equalsIgnoreCase("B"))
        {
            sc = new Scanner(System.in);
            switch(choice.toUpperCase())
            {
                case "C": break;
                case "R": break;
                default: showMenu(); break;
            }
            choice = sc.nextLine();
        }
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
