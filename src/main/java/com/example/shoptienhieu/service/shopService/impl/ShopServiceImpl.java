package com.example.shoptienhieu.service.shopService.impl;

import com.example.shoptienhieu.entities.Shop;
import com.example.shoptienhieu.repository.CityRepository;
import com.example.shoptienhieu.repository.ShopRepository;
import com.example.shoptienhieu.dto.request.UpdateShopRequest;
import com.example.shoptienhieu.service.shopService.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public void updateShop(UpdateShopRequest shop) {
        Optional<Shop> shopData = shopRepository.findById(shop.getId());

        if(shopData.isPresent()) {
            Shop shop_ = shopData.get();
            shop_.setName(shop.getName());
            shop_.setUpdatedAt(new Date().getTime());
            shopRepository.save(shop_);
        }
    }



}
