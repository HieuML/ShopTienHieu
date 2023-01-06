package com.example.shoptienhieu.repository;

import com.example.shoptienhieu.entities.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {
    public List<ShippingAddress> findByCityId(int cityId);
    public List<ShippingAddress> findByUserId(int userId);
}
