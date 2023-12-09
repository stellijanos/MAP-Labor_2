package com.online_shop.MAP_Labor_2_Spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString
@Table(name = "categories")
public class Category {

    @Setter
    private Integer id;
    @Setter
    private String name;
    private final List<Product> products = new ArrayList<>();
}
