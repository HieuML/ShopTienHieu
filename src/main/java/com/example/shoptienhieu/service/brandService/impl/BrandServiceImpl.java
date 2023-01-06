package com.example.shoptienhieu.service.brandService.impl;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.brandReq.BrandRequest;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Brand;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.BrandRepository;
import com.example.shoptienhieu.service.brandService.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;
    @Override
    public List<IdNameRes> getAll() {
        List<IdNameRes> list = new ArrayList<>();
        List<Brand> brands = brandRepository.findAll();
        brands.stream().forEach(
                (o) -> {
                    IdNameRes idNameRes = new IdNameRes(o.getId(), o.getName());
                    list.add(idNameRes);

                }
        );
        return list;
    }

    @Override
    public Brand getById(int id) {
        return brandRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(TextStatus.BRAND_NOT_FOUND)
        );
    }

    @Override
    public Brand getByName(String name) {
        return brandRepository.findByName(name);
    }

    @Override
    public Brand create(BrandRequest brandRequest) {
        Brand brand = new Brand(brandRequest.getBrandName());
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateById(int id, BrandRequest brandRequest) {
        Brand brand1 = getById(id);
        brand1.setName(brandRequest.getBrandName());
        brand1.setUpdatedAt(new Date().getTime());
        return brandRepository.save(brand1);
    }

    @Override
    public void deleteById(int id) {
        Brand brand = getById(id);
        brandRepository.delete(brand);
    }
}
