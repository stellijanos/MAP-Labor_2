package org.online_shop.controllers;

import java.util.Scanner;

public class DataReader {
    public String readString() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    public Integer readInt() {
        Scanner scanner = new Scanner(System.in);
        Integer input = Integer.parseInt(scanner.nextLine());
        scanner.close();
        return input;
    }

    public Float readFloat() {
        Scanner scanner = new Scanner(System.in);
        Float input = Float.parseFloat(scanner.nextLine());
        scanner.close();
        return input;
    }
}
