package com.example.shoptienhieu.dto.response;

import com.example.shoptienhieu.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductSearchDTO {
    private String productID;
    private String productName;
    private int price;
    private String slug;
    private BigDecimal rating;

    public static ProductSearchDTO convertFromEntity(Product product){
        return new ProductSearchDTO(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getSlug(),
                product.getRating()
                );
    }
}
