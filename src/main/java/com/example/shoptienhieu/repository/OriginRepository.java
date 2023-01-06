package com.example.shoptienhieu.repository;

import com.example.shoptienhieu.entities.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OriginRepository extends JpaRepository<Origin,Integer> {
    public Origin findByName(String name);
}