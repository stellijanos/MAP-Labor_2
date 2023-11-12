package org.online_shop.controllers;

import java.util.HashMap;

public class Route {

    private String _currentPath = "/";
    private final HashMap<String, Runnable> paths = new HashMap<>();

    public void definePath(String path, Runnable function) {
        paths.put(path, function);
    }

    public void set_currentPath(String path) {
        _currentPath = path;
    }

    public void getRoute() {
        Runnable function = paths.get(_currentPath);

        if (function != null) {
            function.run();
        } else {
            System.out.println("No route found for path \"" + _currentPath + "\".");
        }
    }

    public void get(String currentPath) {
        Runnable function = paths.get(currentPath);

        if (function != null) {
            function.run();
        } else {
            System.out.println("No route found for path \"" + currentPath + "\".");
        }
    }
}
