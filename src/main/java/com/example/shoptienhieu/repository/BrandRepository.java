package com.example.shoptienhieu.repository;

import com.example.shoptienhieu.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    public Brand findByName(String name);
}
