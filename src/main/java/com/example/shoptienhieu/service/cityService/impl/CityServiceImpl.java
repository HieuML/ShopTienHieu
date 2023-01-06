package com.example.shoptienhieu.service.cityService.impl;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.entities.City;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.CityRepository;
import com.example.shoptienhieu.service.cityService.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City getById(int id) {
        return cityRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(TextStatus.CITY_NOT_FOUND)
        );
    }

    @Override
    public City getByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public City create(String name) {
        return cityRepository.save(new City(name));
    }

    @Override
    public City updateById(int id, String name) {
        City city = getById(id);
        city.setName(name);
        city.setUpdatedAt(new Date().getTime());
        return cityRepository.save(city);
    }

    @Override
    public void deleteById(int id) {
        City city = getById(id);
        cityRepository.delete(city);
    }
}
