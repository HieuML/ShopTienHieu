package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.dto.request.paymentReq.PaymentReq;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Payment;
import com.example.shoptienhieu.service.paymentService.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @GetMapping("findAll")
    public ResponseEntity<List<IdNameRes>> getAllDelivery(){
        List<IdNameRes> payments = paymentService.getAll();
        if(payments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payments,HttpStatus.OK);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getDeliveryById(@PathVariable(name = "paymentId") int paymentId){
        return new ResponseEntity(paymentService.getById(paymentId), HttpStatus.OK);
    }
//    @PreAuthorize("")
    @PostMapping("/create")
    public ResponseEntity<Payment> create(@RequestBody PaymentReq payment){
        Payment payment1 = paymentService.create(payment);
        if(payment1 == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payment1, HttpStatus.OK);
    }
//    @PreAuthorize("")
    @PutMapping("update")
    public ResponseEntity<Payment> updateById(@Valid @RequestBody PaymentReq payment){
        return new ResponseEntity<>(paymentService.updateById( payment), HttpStatus.OK);
    }
//    @PreAuthorize("")
    @DeleteMapping("delete/{paymentId}")
    public ResponseEntity<?>deleteById(@PathVariable("paymentId") int id){
        paymentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
