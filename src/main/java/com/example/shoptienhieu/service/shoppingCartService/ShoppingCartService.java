package com.example.shoptienhieu.service.shoppingCartService;

import com.example.shoptienhieu.dto.request.shoppingCartReq.ShoppingCartRequest;
import com.example.shoptienhieu.entities.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    public List<ShoppingCart> getAll();
    public ShoppingCart getById(int id);
    public ShoppingCart getByUserId(int userId);
    public ShoppingCart create(ShoppingCartRequest shoppingCartRequest);
    public ShoppingCart updateById(int id, ShoppingCartRequest shoppingCartRequest);
    public void deleteById(int id);
    public void checkShoppingCart(ShoppingCartRequest shoppingCartRequest);
}
