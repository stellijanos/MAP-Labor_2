//package com.online_shop.MAP_Labor_2_Spring.models;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Setter
//@Getter
//@Entity
//@ToString
//@Table(name = "products")
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private String name;
//    private Float price;
//    @ManyToOne
//    @JoinColumn(name = "category_id", referencedColumnName = "id")
//    private Category category;
//    private String description;
//    private String imageLink;
//    private Integer stock;
//    @ManyToMany(mappedBy = "favourites")
//    private List<ProductSpec> productSpecs = new ArrayList<>();
//}
