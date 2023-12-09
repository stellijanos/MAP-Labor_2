package com.online_shop.MAP_Labor_2_Spring.models;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "category_id")
    private Category category;
    @Setter
    private String description;
    @Setter
    private String imageLink;
    @Setter
    private Integer stock;

    @ManyToMany(mappedBy = "favourites")
    private final List<ProductSpec> productSpecs;

    public Product() {
        productSpecs = new ArrayList<>();
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Integer getStock() {
        return stock;
    }

    public List<ProductSpec> getProductSpecs() {
        return productSpecs;
    }

    public void setProductSpecs(ProductSpec productSpec) {
        this.productSpecs.add(productSpec);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", stock=" + stock +
                ", productSpecs=" + productSpecs +
                '}';
    }
}
