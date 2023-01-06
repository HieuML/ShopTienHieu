package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.dto.request.deliveryReq.DeliveryRequest;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Delivery;
import com.example.shoptienhieu.service.deliveryService.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;
    @GetMapping("findAll")
    public ResponseEntity<List<IdNameRes>> getAllDelivery(){
        List<IdNameRes> deliveries = deliveryService.getAll();
        if(deliveries.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(deliveries,HttpStatus.OK);
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable(name = "deliveryId") int deliveryId){
        return new ResponseEntity(deliveryService.getById(deliveryId), HttpStatus.OK);
    }
//    @PreAuthorize("")
    @PostMapping("/create")
    public ResponseEntity<Delivery> create(@RequestBody DeliveryRequest delivery){
        Delivery delivery1 = deliveryService.create(delivery);
        if(delivery1 == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(delivery1, HttpStatus.OK);
    }
//    @PreAuthorize("")
    @PutMapping("/update")
    public ResponseEntity<Delivery> updateById(@Valid @RequestBody DeliveryRequest delivery){
        return new ResponseEntity<>(deliveryService.updateById( delivery), HttpStatus.OK);
    }
//    @PreAuthorize("")
    @DeleteMapping("delete/{deliveryId}")
    public ResponseEntity<?>deleteById(@PathVariable("deliveryId") int id){
        deliveryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
