package org.online_shop.controllers;

import static java.lang.Thread.sleep;

public class Controller {

    enum Response {
        USER_EXISTS,
        USER_CREATED_SUCCESSFULLY,
        USER_UPDATED_SUCCESSFULLY,
        USER_DELETED_SUCCESSFULLY,
        USER_NOT_FOUND,
        SOMETHING_WENT_WRONG,
        INCORRECT_EMAIL,
        INCORRECT_PASSWORD,
        LOGIN_SUCCESSFUL,
        SESSION_DESTROY
    }


//    protected String readString() {
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextLine();
//    }
//
//    protected Integer readInteger() {
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextInt();
//    }
//
//    protected Float readFloat() {
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextFloat();
//    }
}
