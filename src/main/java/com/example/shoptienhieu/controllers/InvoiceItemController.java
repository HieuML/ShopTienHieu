package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.entities.InvoiceItem;
import com.example.shoptienhieu.service.invoiceItemService.InvoiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/invoiceItem")
public class InvoiceItemController {
    @Autowired
    InvoiceItemService invoiceItemService;
    @GetMapping("/search")
    public ResponseEntity<List<InvoiceItem>> getALl() {
        List<InvoiceItem> invoiceItems = invoiceItemService.getAll();
        if (invoiceItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(invoiceItems, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<InvoiceItem> getById(@PathVariable("id") int id) {
        InvoiceItem InvoiceItem = invoiceItemService.getById(id);
        if (InvoiceItem == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InvoiceItem, HttpStatus.OK);
    }

    @GetMapping("/details/{invoiceId}")
    public ResponseEntity<List<InvoiceItem>> getByOrderId(@PathVariable("orderId") String id){
        List<InvoiceItem> InvoiceItems = invoiceItemService.getByInvoiceId(id);
        if(InvoiceItems.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InvoiceItems, HttpStatus.OK);
    }
    @GetMapping("/details/{productId}")
    public ResponseEntity<List<InvoiceItem>> getByProductId(@PathVariable("productId") String id){
        List<InvoiceItem> InvoiceItems = invoiceItemService.getByProductId(id);
        if(InvoiceItems.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InvoiceItems, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id ){
        invoiceItemService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
