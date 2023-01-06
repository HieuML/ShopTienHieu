package com.example.shoptienhieu.dto.request.productCommentReq;

import lombok.Data;

@Data
public class ProductCommentRequest {
    private int userId;
    private String productId;
    private String content;
    private String image;
    private Float rating;
}
