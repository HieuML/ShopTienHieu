package com.example.shoptienhieu.service.productImageService.impl;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.productImageReq.ProductImageRequest;
import com.example.shoptienhieu.entities.Product;
import com.example.shoptienhieu.entities.ProductImage;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.ProductImageRepository;
import com.example.shoptienhieu.repository.ProductRepository;
import com.example.shoptienhieu.service.productImageService.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    ProductImageRepository productImageRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<ProductImage> getAll() {
        return productImageRepository.findAll();
    }

    @Override
    public ProductImage getById(int id) {
        return productImageRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(TextStatus.PRODUCT_IMAGE_NOT_FOUND)
        );
    }

    @Override
    public ProductImage create(ProductImageRequest productImageRequest) {
        checkProductImage(productImageRequest);
        return productImageRepository.save(new ProductImage(productImageRequest.getUrl(), productRepository.findById(productImageRequest.getProductId()).get()));
    }

    @Override
    public void deleteById(int id) {
        ProductImage productImage = getById(id);
        productImageRepository.delete(productImage);
    }

    @Override
    public void checkProductImage(ProductImageRequest productImageRequest) {
        Optional<Product> product = productRepository.findById(productImageRequest.getProductId());
        if(!product.isPresent()){
            throw new ResourceNotFoundException(TextStatus.PRODUCT_NOT_FOUND);
        }
    }
}
