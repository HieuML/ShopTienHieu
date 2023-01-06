package com.example.shoptienhieu.service.shippingAddressService.impl;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.shippingAddressReq.ShippingAddressRequest;
import com.example.shoptienhieu.entities.City;
import com.example.shoptienhieu.entities.ShippingAddress;
import com.example.shoptienhieu.entities.User;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.CityRepository;
import com.example.shoptienhieu.repository.ShippingAddressRepository;
import com.example.shoptienhieu.repository.UserRepository;
import com.example.shoptienhieu.service.shippingAddressService.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    ShippingAddressRepository shippingAddressRepository;

    @Override
    public List<ShippingAddress> getAll() {
        return shippingAddressRepository.findAll();
    }

    @Override
    public ShippingAddress getById(int id) {
        return shippingAddressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(TextStatus.SHIPPING_ADDRESS_NOT_FOUND)
        );
    }

    @Override
    public void deleteById(int id) {
        ShippingAddress shippingAddress = getById(id);
        shippingAddressRepository.delete(shippingAddress);
    }

    @Override
    public List<ShippingAddress> getByCityId(int cityId) {
        return shippingAddressRepository.findByCityId(cityId);
    }

    @Override
    public List<ShippingAddress> getByUserId(int userId) {
        return shippingAddressRepository.findByUserId(userId);
    }

    @Override
    public ShippingAddress create(ShippingAddressRequest shippingAddressRequest) {
        checkShippingAddress(shippingAddressRequest);
        return shippingAddressRepository.save(new ShippingAddress(userRepository.findById(shippingAddressRequest.getUserId()).get()
                , cityRepository.findById(shippingAddressRequest.getCityId()).get()
                , shippingAddressRequest.isType(), shippingAddressRequest.isDefault()
                , shippingAddressRequest.getAddressDetail(), shippingAddressRequest.getPhone()));
    }

    @Override
    public ShippingAddress updateById(int id, ShippingAddressRequest shippingAddressRequest) {
        checkShippingAddress(shippingAddressRequest);
        ShippingAddress shippingAddress = getById(id);
        shippingAddress.setUser(userRepository.findById(shippingAddressRequest.getUserId()).get());
        shippingAddress.setCity(cityRepository.findById(shippingAddressRequest.getCityId()).get());
        shippingAddress.setUpdatedAt(new Date().getTime());
        shippingAddress.setDefault(shippingAddressRequest.isDefault());
        shippingAddress.setPhone(shippingAddress.getPhone());
        shippingAddress.setAddressDetail(shippingAddress.getAddressDetail());
        shippingAddress.setType(shippingAddress.isType());
        return shippingAddressRepository.save(shippingAddress);
    }

    @Override
    public void checkShippingAddress(ShippingAddressRequest shippingAddressRequest) {
        Optional<User> user = userRepository.findById(shippingAddressRequest.getUserId());
        if (!user.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.USER_NOT_FOUND);
        }
        Optional<City> city = cityRepository.findById(shippingAddressRequest.getCityId());
        if (!city.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.CITY_NOT_FOUND);
        }
    }
}
