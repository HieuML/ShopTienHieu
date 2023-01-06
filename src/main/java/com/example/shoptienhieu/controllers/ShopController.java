package com.example.shoptienhieu.controllers;


import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.dto.request.UpdateShopRequest;
import com.example.shoptienhieu.service.shopService.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @PutMapping
    public ResponseEntity<String> updateShop (@RequestBody UpdateShopRequest shop) throws ResourceNotFoundException {

        shopService.updateShop(shop);
        return new ResponseEntity<>("Update shop successfully", HttpStatus.OK);

    }



}
