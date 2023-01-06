package com.example.shoptienhieu.repository;

import com.example.shoptienhieu.entities.Product;
import com.example.shoptienhieu.entities.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    /**
     * find products by name
     * @param productName
     * @return
     */
    List<Product> findAllByNameContaining(String productName);

    /**
     * find products by information search
     * @param productName
     * @param price
     * @param slug
     * @return
     */
    Page<Product> findByNameContainingAndPriceAndSlug(String productName, int price, String slug, Pageable pageable);

    Page<Product> findByShopId(Integer shopID, Pageable pageable);

    List<Product> findByShopId(Integer shopID);

    @Query(value = "SELECT t from Product t where t.name like %?1% and t.shop = ?2 ")
    Page<Product> findByShopIdAndNameContaining(String name, Shop shop , Pageable pageable);


}
