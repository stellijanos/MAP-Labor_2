package com.online_shop.MAP_Labor_2_Spring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString
@Table(name = "products")
public class Product {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Setter
    private String name;
    @Setter
    private Float price;
    @Setter
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @Setter
    private String description;
    @Setter
    private String imageLink;
    @Setter
    private Integer stock;
    @ManyToMany(mappedBy = "favourites")
    private final List<ProductSpec> productSpecs = new ArrayList<>();
}
