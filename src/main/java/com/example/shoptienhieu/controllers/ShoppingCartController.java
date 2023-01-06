package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.dto.request.shoppingCartReq.ShoppingCartRequest;
import com.example.shoptienhieu.entities.ShoppingCart;
import com.example.shoptienhieu.service.shoppingCartService.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/shoppingCart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("findAll")
    public ResponseEntity<List<ShoppingCart>> getAll() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAll();
        if (shoppingCarts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @GetMapping("/details/{userid}")
    public ResponseEntity<ShoppingCart> getByUserId(@PathVariable("userid") int id) {
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(id);
        if (shoppingCart == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @GetMapping("/{shoppingCartId}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable(name = "shoppingCartId") int shoppingCartId) {
        return new ResponseEntity(shoppingCartService.getById(shoppingCartId), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PostMapping("/create")
    public ResponseEntity<ShoppingCart> create(@RequestBody ShoppingCartRequest shoppingCartRequest) {
        ShoppingCart shoppingCart1 = shoppingCartService.create(shoppingCartRequest);
        if (shoppingCart1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shoppingCart1, HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PutMapping("update/{shoppingCartId}")
    public ResponseEntity<ShoppingCart> updateById(@PathVariable("shoppingCartId") int shoppingCartId, @Valid @RequestBody ShoppingCartRequest shoppingCartRequest) {
        return new ResponseEntity<>(shoppingCartService.updateById(shoppingCartId, shoppingCartRequest), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @DeleteMapping("delete/{shoppingCartId}")
    public ResponseEntity<?> deleteById(@PathVariable("shoppingCartId") int id) {
        shoppingCartService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
