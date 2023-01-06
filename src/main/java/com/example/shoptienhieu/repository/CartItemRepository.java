package com.example.shoptienhieu.repository;

import com.example.shoptienhieu.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    public List<CartItem> findByProductId(int productId);
    public List<CartItem> findByShoppingCartId(int shoppingCartId);
}
