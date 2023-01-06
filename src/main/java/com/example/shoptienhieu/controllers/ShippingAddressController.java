package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.dto.request.shippingAddressReq.ShippingAddressRequest;
import com.example.shoptienhieu.entities.ShippingAddress;
import com.example.shoptienhieu.service.shippingAddressService.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/shippingAddress")
public class ShippingAddressController {
    @Autowired
    ShippingAddressService shippingAddressService;

    @GetMapping("findAll")
    public ResponseEntity<List<ShippingAddress>> getAll() {
        List<ShippingAddress> shippingAddresses = shippingAddressService.getAll();
        if (shippingAddresses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shippingAddresses, HttpStatus.OK);
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<ShippingAddress>> getByCityId(@PathVariable("cityId") int id) {
        List<ShippingAddress> shippingAddresses = shippingAddressService.getByCityId(id);
        if (shippingAddresses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shippingAddresses, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ShippingAddress>> getByUserId(@PathVariable("userId") int id) {
        List<ShippingAddress> shippingAddresses = shippingAddressService.getByUserId(id);
        if (shippingAddresses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shippingAddresses, HttpStatus.OK);
    }

    @GetMapping("/{shippingAddressId}")
    public ResponseEntity<ShippingAddress> getShippingAddressById(@PathVariable(name = "shippingAddressId") int shippingAddressId) {
        return new ResponseEntity(shippingAddressService.getById(shippingAddressId), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PostMapping("/create")
    public ResponseEntity<ShippingAddress> create(@RequestBody ShippingAddressRequest shippingAddressRequest) {
        ShippingAddress shippingAddress1 = shippingAddressService.create(shippingAddressRequest);
        if (shippingAddress1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shippingAddress1, HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PutMapping("update/{shippingAddressId}")
    public ResponseEntity<ShippingAddress> updateById(@PathVariable("shippingAddressId") int shippingAddressId
            , @Valid @RequestBody ShippingAddressRequest shippingAddressRequest) {
        return new ResponseEntity<>(shippingAddressService.updateById(shippingAddressId, shippingAddressRequest), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @DeleteMapping("delete/{ShippingAddressId}")
    public ResponseEntity<?> deleteById(@PathVariable("ShippingAddressId") int id) {
        shippingAddressService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
