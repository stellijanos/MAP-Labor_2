package org.online_shop.controllers;

import java.util.HashMap;

public class Route {

    private final HashMap<String, Runnable> paths = new HashMap<>();

    public void definePath(String path, Runnable function) {
        paths.put(path, function);
    }

    protected void get(String currentPath) {
        Runnable function = paths.get(currentPath);

        if (function != null) {
            function.run();
        } else {
            System.out.println("No route found for path \"" + currentPath + "\".");
        }
    }
}
