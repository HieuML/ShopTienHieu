package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.constants.Path;
import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.productReq.CreateOrUpdateProductReq;
import com.example.shoptienhieu.dto.request.productReq.GetALLProductReq;
import com.example.shoptienhieu.dto.response.BaseRes;
import com.example.shoptienhieu.dto.response.BaseUpdatedRes;
import com.example.shoptienhieu.service.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {
    @PostMapping  (Path.GET_ALL)
    public ResponseEntity<?> getAllProductByShop(@RequestBody GetALLProductReq req ) {
        return ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(),TextStatus.FIND_PRODUCT_BY_SHOPID_SUCCESS,productService.getAllProduct(req)));
    }
    @Autowired
    private ProductService productService;
    @DeleteMapping(Path.INFO)
    public ResponseEntity<?> getDetailProduct ( @PathVariable String productId)  {
        return  ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.GET_DETAIL_PRODUCT_SUCCESS,productService.getDetailProduct(productId)));
    }
    @PostMapping(Path.CREATE)
    public ResponseEntity<?> createProduct ( @RequestBody CreateOrUpdateProductReq product)  {
        System.out.println("get create");
        productService.createProduct(product);
        return  ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.CREATE_PRODUCT_SUCCESS));
    }

    @PutMapping(Path.UPDATE)
    public ResponseEntity<?> updateProduct ( @RequestBody CreateOrUpdateProductReq product)  {
        productService.updateProduct(product);
        return  ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.UPDATE_PRODUCT_SUCCESS,new BaseUpdatedRes(product.getProductId(),true)));
    }

    @PutMapping(Path.CHANGE_STATUS)
    public ResponseEntity<?> changeStatus ( @PathVariable String productId)  {
        productService.changeStatus(productId);
        return  ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.CHANGE_STATUS_PRODUCT_SUCCESS,new BaseUpdatedRes(productId,true)));
    }

    @DeleteMapping(Path.DELETE)
    public ResponseEntity<?> deleteProduct ( @PathVariable String productId) {
        productService.deleteProduct(productId);
        return  ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.DELETE_PRODUCT_SUCCESS));
    }
}
