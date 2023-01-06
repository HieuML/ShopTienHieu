package com.example.shoptienhieu.controllers;


import com.example.shoptienhieu.dto.request.productCommentReq.ProductCommentRequest;
import com.example.shoptienhieu.entities.ProductComment;
import com.example.shoptienhieu.service.productCommentService.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/productComment")
public class ProductCommentController {
    @Autowired
    ProductCommentService productCommentService;

    @GetMapping("findAll")
    public ResponseEntity<List<ProductComment>> getAll() {
        List<ProductComment> productComments = productCommentService.getAll();
        if (productComments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productComments, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductComment>> getByProductId(@PathVariable("productId") int id) {
        List<ProductComment> productComments = productCommentService.getByProductId(id);
        if (productComments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productComments, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductComment>> getByUserId(@PathVariable("userId") int id) {
        List<ProductComment> productComments = productCommentService.getByUserId(id);
        if (productComments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productComments, HttpStatus.OK);
    }

    @GetMapping("/{productCommentId}")
    public ResponseEntity<ProductComment> getProductCommentById(@PathVariable(name = "productCommentId") int productCommentId) {
        return new ResponseEntity(productCommentService.getById(productCommentId), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PostMapping("/create")
    public ResponseEntity<ProductComment> create(@RequestBody ProductCommentRequest productCommentRequest) {
        ProductComment productComment1 = productCommentService.create(productCommentRequest);
        if (productComment1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productComment1, HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PutMapping("update/{productCommentId}")
    public ResponseEntity<ProductComment> updateById(@PathVariable("productCommentId") int productCommentId, @Valid @RequestBody ProductCommentRequest productCommentRequest) {
        return new ResponseEntity<>(productCommentService.updateById(productCommentId, productCommentRequest), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @DeleteMapping("delete/{productCommentId}")
    public ResponseEntity<?> deleteById(@PathVariable("productCommentId") int id) {
        productCommentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

