package org.online_shop.controllers;

public class Controller {

    enum Response {
        USER_EXISTS,
        USER_NOT_FOUND,
        USER_CREATED_SUCCESSFULLY,
        USER_UPDATED_SUCCESSFULLY,
        USER_DELETED_SUCCESSFULLY,
        SOMETHING_WENT_WRONG,
        INCORRECT_EMAIL,
        INCORRECT_PASSWORD,
        PASSWORDS_DO_NOT_MATCH,
        PASSWORD_UPDATED_SUCCESSFULLY,
        LOGIN_SUCCESSFUL
    }
}
