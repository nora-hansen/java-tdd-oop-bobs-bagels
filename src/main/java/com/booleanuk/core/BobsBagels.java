package com.booleanuk.core;

import java.util.Scanner;

public class BobsBagels {
    String mainMenu;
    String managerMenu;
    String shopMenu;
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
                Input: """;
        this.managerMenu = """
                Manager actions:
                    (C)hange basket size
                    (R)estock
                    (B)ack to main menu
                    (Q)uit
                Input: """;

        this.sc = new Scanner(System.in);
    }

}
