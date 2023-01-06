package com.example.shoptienhieu.service.cartItemService.impl;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.cartItemReq.CartItemRequest;
import com.example.shoptienhieu.entities.CartItem;
import com.example.shoptienhieu.entities.Product;
import com.example.shoptienhieu.entities.ShoppingCart;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.CartItemRepository;
import com.example.shoptienhieu.repository.ProductRepository;
import com.example.shoptienhieu.repository.ShoppingCartRepository;
import com.example.shoptienhieu.service.cartItemService.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getById(int id) {
        return cartItemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(TextStatus.CART_ITEM_NOT_FOUND)
        );
    }

    @Override
    public void deleteById(int id) {
        CartItem cartItem = getById(id);
        cartItemRepository.delete(cartItem);
    }

    @Override
    public List<CartItem> getByShoppingCartId(int shoppingCartId) {
        return cartItemRepository.findByShoppingCartId(shoppingCartId);
    }

    @Override
    public List<CartItem> getByProductId(int productId) {
        return cartItemRepository.findByProductId(productId);
    }

    @Override
    public CartItem create(CartItemRequest cartItemRequest) {
        checkCartItem(cartItemRequest);
        return cartItemRepository.save(new CartItem(shoppingCartRepository.findById(cartItemRequest.getShoppingCartId()).get()
                ,productRepository.findById(cartItemRequest.getProductId()).get()
                ,cartItemRequest.getQuantity() ,cartItemRequest.isCheck()));
    }

    @Override
    public CartItem updateById(int id, CartItemRequest cartItemRequest) {
        checkCartItem(cartItemRequest);
        CartItem cartItem = getById(id);
        cartItem.setShoppingCart(shoppingCartRepository.findById(cartItemRequest.getShoppingCartId()).get());
        cartItem.setProduct(productRepository.findById(cartItemRequest.getProductId()).get());
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartItem.setIsCheck(cartItemRequest.isCheck());
        cartItem.setUpdatedAt(new Date().getTime());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void checkCartItem(CartItemRequest cartItemRequest) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(cartItemRequest.getShoppingCartId());
        if (!shoppingCart.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.SHOPPING_CART_NOT_FOUND);
        }
        Optional<Product> product = productRepository.findById(cartItemRequest.getProductId());
        if (!product.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.PRODUCT_NOT_FOUND);
        }
    }
}
