package com.example.shoptienhieu.dto.response.productRes;

import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProductRes {

    private String productID;

    private String name;

    private String description;

    private int price;

    private int origin_price;

    private IdNameRes brand;

    private IdNameRes category;

    private IdNameRes shop;

    private IdNameRes origin;

    private int sold_quantity;

    private int in_stock;

    private java.math.BigDecimal rating;

    private String slug;

    public ProductRes(Product _product) {
        this.productID = _product.getId();
        this.name = _product.getName();
        this.description = _product.getDescription();
        this.price = price;
        this.origin_price = origin_price;
        this.brand = brand;
        this.category = category;
        this.shop = shop;
        this.origin = origin;
        this.sold_quantity = sold_quantity;
        this.in_stock = in_stock;
        this.rating = rating;
        this.slug = slug;
    }
}
