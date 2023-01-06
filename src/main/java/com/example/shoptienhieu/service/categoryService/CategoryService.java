package com.example.shoptienhieu.service.categoryService;

import com.example.shoptienhieu.dto.request.categpryReq.CategoryRequest;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public List<IdNameRes> getAll();
    public Category getById(int id);
    public Category getByName(String name);
    public Category create(CategoryRequest categoryRequest);
    public Category updateById(int id, CategoryRequest categoryRequest);
    public void deleteById(int id);
}
