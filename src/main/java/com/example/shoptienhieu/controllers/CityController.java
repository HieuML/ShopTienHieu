package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.entities.City;
import com.example.shoptienhieu.service.cityService.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/city")
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("findAll")
    public ResponseEntity<List<City>> getAll() {
        List<City> cities = cityService.getAll();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
    @GetMapping("/{cityId}")
    public ResponseEntity<City> getById(@PathVariable(name = "cityId") int cityId) {
        return new ResponseEntity(cityService.getById(cityId), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PostMapping("/create")
    public ResponseEntity<City> create(@RequestBody String name) {
        City city1 = cityService.create(name);
        if (city1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(city1, HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PutMapping("update/{cityId}")
    public ResponseEntity<City> updateById(@PathVariable("cityId") int cityId, @Valid @RequestBody String name) {
        return new ResponseEntity<>(cityService.updateById(cityId, name), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @DeleteMapping("delete/{cityId}")
    public ResponseEntity<?> deleteById(@PathVariable("cityId") int id) {
        cityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
