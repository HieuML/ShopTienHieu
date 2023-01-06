package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.constants.Path;
import com.example.shoptienhieu.dto.request.brandReq.BrandRequest;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Brand;
import com.example.shoptienhieu.service.brandService.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/brand")
public class BrandController {
    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    BrandService brandService;

    @GetMapping(Path.GET_ALL)
    public ResponseEntity<List<IdNameRes>> getAllBrand() {
        logger.info("Request to get all");
        List<IdNameRes> brands = brandService.getAll();
        if (brands.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<Brand> getBrandById(@PathVariable(name = "brandId") int brandId) {
        logger.info("Request to find by id");
        return new ResponseEntity(brandService.getById(brandId), HttpStatus.OK);
    }

    //    @PreAuthorize("")
    @PostMapping("/create")
    public ResponseEntity<Brand> create(@RequestBody BrandRequest brandRequest) {
        logger.info("Request to create");

        Brand Brand1 = brandService.create(brandRequest);
        if (Brand1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(Brand1, HttpStatus.OK);
    }

    //    @PreAuthorize("")
    @PutMapping("update/{brandId}")
    public ResponseEntity<Brand> updateById(@PathVariable("brandId") int brandId, @Valid @RequestBody BrandRequest brandRequest) {
        logger.info("Request to update");

        return new ResponseEntity<>(brandService.updateById(brandId, brandRequest), HttpStatus.OK);
    }

    //    @PreAuthorize("")
    @DeleteMapping("delete/{brandId}")
    public ResponseEntity<?> deleteById(@PathVariable("brandId") int id) {
        logger.info("Request to delete");

        brandService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
