package com.example.shoptienhieu.service.cityService;

import com.example.shoptienhieu.entities.City;

import java.util.List;

public interface CityService {
    public List<City> getAll();
    public City getById(int id);
    public City getByName(String name);
    public City create(String name);
    public City updateById(int id, String name);
    public void deleteById(int id);
}
