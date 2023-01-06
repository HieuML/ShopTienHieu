package com.example.shoptienhieu.service.productCommentService.impl;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.productCommentReq.ProductCommentRequest;
import com.example.shoptienhieu.entities.Product;
import com.example.shoptienhieu.entities.ProductComment;
import com.example.shoptienhieu.entities.User;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.ProductCommentRepository;
import com.example.shoptienhieu.repository.ProductRepository;
import com.example.shoptienhieu.repository.UserRepository;
import com.example.shoptienhieu.service.productCommentService.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCommentServiceImpl implements ProductCommentService {
    @Autowired
    ProductCommentRepository productCommentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductComment> getAll() {
        return productCommentRepository.findAll();
    }

    @Override
    public ProductComment getById(int id) {
        return productCommentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(TextStatus.PRODUCT_COMMENT_NOT_FOUND)
        );
    }

    @Override
    public void deleteById(int id) {
        ProductComment productComment = getById(id);
        productCommentRepository.delete(productComment);
    }

    @Override
    public List<ProductComment> getByUserId(int userId) {
        return productCommentRepository.findByUserId(userId);
    }

    @Override
    public List<ProductComment> getByProductId(int productId) {
        return productCommentRepository.findByProductId(productId);
    }

    @Override
    public ProductComment create(ProductCommentRequest productCommentRequest) {
        checkProductComment(productCommentRequest);
        return productCommentRepository.save(new ProductComment(userRepository.findById(productCommentRequest.getUserId()).get()
                , productRepository.findById(productCommentRequest.getProductId()).get()
                , productCommentRequest.getContent(), productCommentRequest.getImage(), productCommentRequest.getRating()));
    }

    @Override
    public ProductComment updateById(int id, ProductCommentRequest productCommentRequest) {
        checkProductComment(productCommentRequest);
        ProductComment productComment = getById(id);
        productComment.setProduct(productRepository.findById(productCommentRequest.getProductId()).get());
        productComment.setUser(userRepository.findById(productCommentRequest.getUserId()).get());
        productComment.setImage(productCommentRequest.getImage());
        productComment.setContent(productCommentRequest.getContent());
        productComment.setUpdatedAt(new Date().getTime());
        productComment.setRating(productCommentRequest.getRating());
        return productCommentRepository.save(productComment);
    }

    @Override
    public void checkProductComment(ProductCommentRequest productCommentRequest) {
        Optional<Product> product = productRepository.findById(productCommentRequest.getProductId());
        if (!product.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.PRODUCT_NOT_FOUND);
        }
        Optional<User> user = userRepository.findById(productCommentRequest.getUserId());
        if (!user.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.USER_NOT_FOUND);
        }
    }
}
