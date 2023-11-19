package org.online_shop.models;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private Integer id;
    private String name;
    private Float price;
    private Category category;
    private String description;
    private String imageLink;
    private Integer stock;
    private final List<ProductSpec> productSpecs = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<ProductSpec> getProductSpecs() {
        return productSpecs;
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
