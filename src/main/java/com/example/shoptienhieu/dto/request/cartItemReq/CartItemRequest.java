package com.example.shoptienhieu.dto.request.cartItemReq;

import lombok.Data;

@Data
public class CartItemRequest {
    private int shoppingCartId;
    private String productId;
    private int quantity;
    private boolean isCheck;
}
