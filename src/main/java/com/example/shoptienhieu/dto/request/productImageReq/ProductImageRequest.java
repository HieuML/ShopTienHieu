package com.example.shoptienhieu.dto.request.productImageReq;

import lombok.Data;

@Data
public class ProductImageRequest {
    private String url;
    private String productId;
    private boolean isDefault;
}
