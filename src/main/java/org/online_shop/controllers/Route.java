package org.online_shop.controllers;

import java.util.HashMap;

public class Route {

    public String currentPath = "/";
    public HashMap<String, Runnable> paths = new HashMap<>();

    public void definePath(String path, Runnable function) {
        paths.put(path, function);
    }

    public void setCurrentPath(String path) {
        currentPath = path;
    }

    public void getRoute() {
        Runnable function = paths.get(currentPath);

        if (function != null) {
            function.run();
        } else {
            System.out.println("No route found for path \"" + currentPath + "\".");
        }
    }
}
