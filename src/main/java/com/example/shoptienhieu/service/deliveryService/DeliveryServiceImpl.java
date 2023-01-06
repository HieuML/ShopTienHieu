package com.example.shoptienhieu.service.deliveryService;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.deliveryReq.DeliveryRequest;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Delivery;
import com.example.shoptienhieu.entities.Origin;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Override
    public List<IdNameRes> getAll() {
        List<Delivery> deliveries =  deliveryRepository.findAll();
        List<IdNameRes> list = new ArrayList<>();
        deliveries.stream().forEach(
                o->{
                    IdNameRes idNameRes = new IdNameRes(o.getId(), o.getName());
                    list.add(idNameRes);
                }
        );
        return list;
    }

    @Override
    public Delivery getById(int id) {
        return deliveryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(TextStatus.DELIVERY_NOT_FOUND));
    }

    @Override
    public Delivery create(DeliveryRequest deliveryRequest) {
        return deliveryRepository.save(new Delivery(deliveryRequest.getName()));
    }

    @Override
    public Delivery updateById(DeliveryRequest deliveryRequest) {
        Delivery delivery = getById(deliveryRequest.getId());
        delivery.setName(deliveryRequest.getName());
        delivery.setCreatedAt(new Date().getTime());
        return deliveryRepository.save(delivery);
    }

    @Override
    public void deleteById(int id) {
        Delivery delivery = getById(id);
        deliveryRepository.delete(delivery);
    }
}
