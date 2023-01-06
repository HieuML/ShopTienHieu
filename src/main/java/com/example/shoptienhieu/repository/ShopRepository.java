package com.example.shoptienhieu.repository;

import com.example.shoptienhieu.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Optional<Shop> findById(int id);

    Shop findByUserId(Integer userID);

}