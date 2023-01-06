package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.dto.request.productImageReq.ProductImageRequest;
import com.example.shoptienhieu.entities.ProductImage;
import com.example.shoptienhieu.service.productImageService.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product_image")
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;

    @GetMapping("findAll")
    public ResponseEntity<List<ProductImage>> getAll() {
        List<ProductImage> productImages = productImageService.getAll();
        if (productImages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productImages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductImage> getById(@PathVariable(name = "id") int id) {
        return new ResponseEntity(productImageService.getById(id), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PostMapping("/create")
    public ResponseEntity<ProductImage> create(@RequestBody ProductImageRequest productImageRequest) {
        ProductImage productImage = productImageService.create(productImageRequest);
        if (productImage == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productImage, HttpStatus.OK);
    }

//    @PreAuthorize("")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        productImageService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
