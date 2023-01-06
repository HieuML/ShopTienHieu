package com.example.shoptienhieu.service.cartItemService;

import com.example.shoptienhieu.dto.request.cartItemReq.CartItemRequest;
import com.example.shoptienhieu.entities.CartItem;

import java.util.List;

public interface CartItemService {
    public List<CartItem> getAll();

    public CartItem getById(int id);

    public void deleteById(int id);

    public List<CartItem> getByShoppingCartId(int shoppingCartId);

    public List<CartItem> getByProductId(int productId);

    public CartItem create(CartItemRequest cartItemRequest);

    public CartItem updateById(int id, CartItemRequest cartItemRequest);

    public void checkCartItem(CartItemRequest cartItemRequest);

}
