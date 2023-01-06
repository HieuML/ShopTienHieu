package com.example.shoptienhieu.service.brandService;

import com.example.shoptienhieu.dto.request.brandReq.BrandRequest;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Brand;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BrandService {
    public List<IdNameRes> getAll();
    public Brand getById(int id);
    public Brand getByName(String name);
    public Brand create(BrandRequest brandRequest);
    public Brand updateById(int id, BrandRequest brandRequest);
    public void deleteById(int id);
}
