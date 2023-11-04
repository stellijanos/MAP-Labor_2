package org.online_shop.controllers;

import java.util.Scanner;

public class Controller {

    enum Response {
        USER_EXISTS,
        USER_CREATED_SUCCESSFULLY,
        SOMETHING_WENT_WRONG,
        SESSION_START,
        SESSION_DESTROY
    }

    public String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Integer readInteger() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public Float readFloat() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextFloat();
    }
}
