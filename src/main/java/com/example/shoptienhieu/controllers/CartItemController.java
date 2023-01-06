package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.dto.request.cartItemReq.CartItemRequest;
import com.example.shoptienhieu.entities.CartItem;
import com.example.shoptienhieu.service.cartItemService.CartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/cartItem")
public class CartItemController {
    private static final Logger logger = LoggerFactory.getLogger(CartItemController.class);

    @Autowired
    CartItemService cartItemService;

    @GetMapping("findAll")
    public ResponseEntity<List<CartItem>> getAll() {
        logger.info("Request to find all ");
        List<CartItem> cartItems = cartItemService.getAll();
        if (cartItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<CartItem>> getByProductId(@PathVariable("productId") int id) {
        logger.info("Request to get by product  ");

        List<CartItem> cartItems = cartItemService.getByProductId(id);
        if (cartItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }
    @GetMapping("/user/{shoppingCartId}")
    public ResponseEntity<List<CartItem>> getByShoppingCartId(@PathVariable("shoppingCartId") int id) {
        logger.info("Request to shopping cart id ");

        List<CartItem> cartItems = cartItemService.getByShoppingCartId(id);
        if (cartItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable(name = "cartItemId") int cartItemId) {

        logger.info("Request to cartItem ");
        return new ResponseEntity(cartItemService.getById(cartItemId), HttpStatus.OK);
    }

    //    @PreAuthorize("")
    @PostMapping("/create")
    public ResponseEntity<CartItem> create(@RequestBody CartItemRequest cartItemRequest) {
        logger.info("Request to create ");

        CartItem cartItem1 = cartItemService.create(cartItemRequest);
        if (cartItem1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartItem1, HttpStatus.OK);
    }

    //    @PreAuthorize("")
    @PutMapping("update/{cartItemId}")
    public ResponseEntity<CartItem> updateById(@PathVariable("cartItemId") int cartItemId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        logger.info("Request to update ");

        return new ResponseEntity<>(cartItemService.updateById(cartItemId, cartItemRequest), HttpStatus.OK);
    }

    //    @PreAuthorize("")
    @DeleteMapping("delete/{cartItemId}")
    public ResponseEntity<?> deleteById(@PathVariable("cartItemId") int id) {
        logger.info("Request to delete ");

        cartItemService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
