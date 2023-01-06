package com.example.shoptienhieu.service.originService;

import com.example.shoptienhieu.dto.request.originReq.OriginRequest;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Origin;

import java.util.List;

public interface OriginService {
    public List<IdNameRes> getAll();
    public Origin getById(int id);
    public Origin getByName(String name);
    public Origin create(OriginRequest originRequest);
    public Origin updateById(int id, OriginRequest originRequest);
    public void deleteById(int id);
}
