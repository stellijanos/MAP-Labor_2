package org.online_shop.repositories;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {

    public Dotenv load() {
        return Dotenv.load();
    }
}
