package org.online_shop;

import org.online_shop.controllers.AppController;

public class Main {
    public static void main(String[] args) {
        AppController appController = new AppController();
        appController.run();
    }
}
