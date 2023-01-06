package com.example.shoptienhieu.service.categoryService.impl;


import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.categpryReq.CategoryRequest;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Category;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.CategoryRepository;
import com.example.shoptienhieu.service.categoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<IdNameRes> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<IdNameRes> list = new ArrayList<>();
        categories.stream().forEach(
                o->{
                    IdNameRes idNameRes = new IdNameRes(o.getId(), o.getName());
                    list.add(idNameRes);
                }
        );
        return list;
    }

    @Override
    public Category getById(int id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(TextStatus.CATEGORY_NOT_FOUND)
        );
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category create(CategoryRequest categoryRequest) {
        return categoryRepository.save(new Category(categoryRequest.getCategoryName()));
    }

    @Override
    public Category updateById(int id, CategoryRequest categoryRequest) {
        Category category = getById(id);
        category.setName(categoryRequest.getCategoryName());
        category.setUpdatedAt(new Date().getTime());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(int id) {
        Category category = getById(id);
        categoryRepository.delete(category);
    }
}
