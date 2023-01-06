package com.example.shoptienhieu.service.productCommentService;

import com.example.shoptienhieu.dto.request.productCommentReq.ProductCommentRequest;
import com.example.shoptienhieu.entities.ProductComment;

import java.util.List;

public interface ProductCommentService {
    public List<ProductComment> getAll();

    public ProductComment getById(int id);

    public void deleteById(int id);

    public List<ProductComment> getByUserId(int userId);

    public List<ProductComment> getByProductId(int productId);

    public ProductComment create(ProductCommentRequest productCommentRequest);

    public ProductComment updateById(int id, ProductCommentRequest productCommentRequest);

    public void checkProductComment(ProductCommentRequest productCommentRequest);
}
