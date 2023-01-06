package com.example.shoptienhieu.dto.request.productReq;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateProductReq {
    private String productId;
    @Size(max = 45)
    private String name;
    private String description;
    private int price;
    private int originPrice;
    private int brandId;
    private int categoryId;
    private int shopId;
    private int originId;
    private int inStock;
    private String slug;
}
