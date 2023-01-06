package com.example.shoptienhieu.service.deliveryService;

import com.example.shoptienhieu.dto.request.deliveryReq.DeliveryRequest;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Delivery;

import java.util.List;

public interface DeliveryService {
    public List<IdNameRes> getAll();
    public Delivery getById(int id);
    public Delivery create(DeliveryRequest deliveryRequest);
    public Delivery updateById( DeliveryRequest deliveryRequest);
    public void deleteById(int id);
}
