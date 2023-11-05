package org.online_shop.controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Controller {

    enum Response {
        USER_EXISTS,
        USER_CREATED_SUCCESSFULLY,
        SOMETHING_WENT_WRONG,
        INCORRECT_PASSWORD,
        SESSION_START,
        SESSION_DESTROY
    }


    protected String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    protected Integer readInteger() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    protected Float readFloat() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextFloat();
    }
}
