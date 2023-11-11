package org.online_shop;

import org.online_shop.controllers.AppController;
import org.online_shop.controllers.Route;

public class Main {
    public static void main(String[] args) {

        AppController appController = new AppController();

        appController.setRoute("", appController::quit);
        appController.setRoute("/", appController::mainMenu);
        appController.setRoute("/login", appController::logIn);
        appController.setRoute("/signup", appController::signUp);

        appController.run();
    }
}
