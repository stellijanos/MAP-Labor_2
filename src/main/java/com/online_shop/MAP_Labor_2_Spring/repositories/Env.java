package com.online_shop.MAP_Labor_2_Spring.models.repositories;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {

    public Dotenv load() {
        return Dotenv.load();
    }
}
