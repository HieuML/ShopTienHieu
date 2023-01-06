package com.example.shoptienhieu.service.shopService;

import com.example.shoptienhieu.dto.request.UpdateShopRequest;
import org.springframework.stereotype.Service;

@Service
public interface ShopService {

    public void updateShop(UpdateShopRequest shop);



}
