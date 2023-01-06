package com.example.shoptienhieu.service.originService.impl;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.originReq.OriginRequest;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Origin;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.OriginRepository;
import com.example.shoptienhieu.service.originService.OriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.css.ElementCSSInlineStyle;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OriginServiceImpl implements OriginService {
    @Autowired
    OriginRepository originRepository;
    @Override
    public List<IdNameRes> getAll() {
        List<Origin> origins =  originRepository.findAll();
        List<IdNameRes> list = new ArrayList<>();
        origins.stream().forEach(
                o->{
                    IdNameRes idNameRes = new IdNameRes(o.getId(), o.getName());
                    list.add(idNameRes);
                }
        );
        return list;
    }

    @Override
    public Origin getById(int id) {
        return originRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(TextStatus.ORIGIN_NOT_FOUND)
        );
    }

    @Override
    public Origin getByName(String name) {
        return originRepository.findByName(name);
    }

    @Override
    public Origin create(OriginRequest originRequest) {
        return originRepository.save(new Origin(originRequest.getOriginName()));
    }

    @Override
    public Origin updateById(int id, OriginRequest originRequest) {
        Origin origin = getById(id);
        origin.setName(originRequest.getOriginName());
        origin.setCreatedAt(new Date().getTime());
        return originRepository.save(origin);
    }

    @Override
    public void deleteById(int id) {
        Origin origin = getById(id);
        originRepository.delete(origin);
    }
}
