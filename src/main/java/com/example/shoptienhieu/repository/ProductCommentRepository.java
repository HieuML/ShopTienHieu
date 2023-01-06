package com.example.shoptienhieu.repository;

import com.example.shoptienhieu.entities.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer> {
List<ProductComment> findByUserId(int userId);
List<ProductComment> findByProductId(int productId);
}
