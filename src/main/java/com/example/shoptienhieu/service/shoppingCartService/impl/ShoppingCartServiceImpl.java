package com.example.shoptienhieu.service.shoppingCartService.impl;


import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.shoppingCartReq.ShoppingCartRequest;
import com.example.shoptienhieu.entities.ShoppingCart;
import com.example.shoptienhieu.entities.User;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.ShoppingCartRepository;
import com.example.shoptienhieu.repository.UserRepository;
import com.example.shoptienhieu.service.shoppingCartService.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<ShoppingCart> getAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart getById(int id) {
        return shoppingCartRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(TextStatus.SHOPPING_CART_NOT_FOUND)
        );
    }

    @Override
    public ShoppingCart getByUserId(int id) {
        return shoppingCartRepository.findByUserId(id);
    }

    @Override
    public ShoppingCart create(ShoppingCartRequest shoppingCartRequest) {
        checkShoppingCart(shoppingCartRequest);
        return shoppingCartRepository.save(new ShoppingCart(userRepository.findById(shoppingCartRequest.getUserId()).get()));
    }

    @Override
    public ShoppingCart updateById(int id, ShoppingCartRequest shoppingCartRequest) {
        checkShoppingCart(shoppingCartRequest);
        ShoppingCart shoppingCart = getById(id);
        shoppingCart.setUser(userRepository.findById(shoppingCartRequest.getUserId()).get());
        shoppingCart.setUpdatedAt(new Date().getTime());
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void deleteById(int id) {
        ShoppingCart shoppingCart = getById(id);
        shoppingCartRepository.delete(shoppingCart);
    }

    @Override
    public void checkShoppingCart(ShoppingCartRequest shoppingCartRequest) {
        Optional<User> user = userRepository.findById(shoppingCartRequest.getUserId());
        if(!user.isPresent()){
            throw new ResourceNotFoundException(TextStatus.USER_NOT_FOUND);
        }
    }
}
