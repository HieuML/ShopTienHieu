package com.example.shoptienhieu.service.productImageService;

import com.example.shoptienhieu.dto.request.productImageReq.ProductImageRequest;
import com.example.shoptienhieu.entities.ProductImage;

import java.util.List;

public interface ProductImageService {
    public List<ProductImage> getAll();
    public ProductImage getById(int id);
    public ProductImage create(ProductImageRequest productImageRequest);
    public void deleteById(int id);
    public void checkProductImage(ProductImageRequest productImageRequest);

}
