package com.online_shop.MAP_Labor_2_Spring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Entity
@ToString
@Getter
@Table(name = "product_specs")
public class ProductSpec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @Column(name = "spec_name")
    private String specName;
    @Column(name = "spec_value")
    private String specValue;
}
