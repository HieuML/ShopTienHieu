package com.example.shoptienhieu.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product")
@Setter
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", parameters = {@Parameter(name = "prefix", value = "pd")}, strategy = "com.example.shoptienhieu.utils.generatorIDHandler")
    private String id;
    @Column(length = 45)
    private String name;
    private String description;
    private Integer price;
    @Column(name = "origin_price")
    private Integer originPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id",referencedColumnName = "id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id",referencedColumnName = "id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_id",referencedColumnName = "id")
    private Origin origin;

    @Column(name = "sold_quantity")
    private Integer soldQuantity;

    @Column(name = "in_stock")
    private Integer inStock;


    @Column(precision = 2, scale = 1)
    @Type(type = "big_decimal")
    private BigDecimal rating;

    private String slug;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    public Product(String name, String description, int price, int originPrice, Brand brand, Category category, Shop shop, Origin origin, int inStock, String slug) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.originPrice = originPrice;
        this.brand = brand;
        this.category = category;
        this.shop = shop;
        this.origin = origin;
        this.soldQuantity = 0;
        this.inStock = inStock;
        this.rating = new BigDecimal(0);
        this.slug = slug;
        this.isActive = false;
        this.createdAt = new Date().getTime();
        this.updatedAt = new Date().getTime();
    }
}
