package com.example.shoptienhieu.repository;

import com.example.shoptienhieu.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    public ShoppingCart findByUserId(int userId);
}
