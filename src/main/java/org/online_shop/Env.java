package org.online_shop;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {

    public Dotenv load() {
        return Dotenv.load();
    }
}
