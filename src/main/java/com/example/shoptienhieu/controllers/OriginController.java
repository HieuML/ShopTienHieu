package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.dto.request.originReq.OriginRequest;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Origin;
import com.example.shoptienhieu.service.originService.OriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/origin")
public class OriginController {
    @Autowired
    OriginService originService;

    @GetMapping("findAll")
    public ResponseEntity<List<IdNameRes>> getAllBrand() {
        List<IdNameRes> origins = originService.getAll();
        if (origins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(origins, HttpStatus.OK);
    }

    @GetMapping("/{originId}")
    public ResponseEntity<Origin> getBrandById(@PathVariable(name = "originId") int originId) {
        return new ResponseEntity(originService.getById(originId), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PostMapping("/create")
    public ResponseEntity<Origin> create(@RequestBody OriginRequest originRequest) {
        Origin origin1 = originService.create(originRequest);
        if (origin1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(origin1, HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PutMapping("update/{originId}")
    public ResponseEntity<Origin> updateById(@PathVariable("originId") int originId, @Valid @RequestBody OriginRequest originRequest) {
        return new ResponseEntity<>(originService.updateById(originId, originRequest), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @DeleteMapping("delete/{originId}")
    public ResponseEntity<?> deleteById(@PathVariable("originId") int id) {
        originService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
