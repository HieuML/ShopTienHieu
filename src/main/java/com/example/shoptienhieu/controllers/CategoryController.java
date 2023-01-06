package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.dto.request.categpryReq.CategoryRequest;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Category;
import com.example.shoptienhieu.service.categoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("findAll")
    public ResponseEntity<List<IdNameRes>> getAllBrand() {
        List<IdNameRes> categories = categoryService.getAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getBrandById(@PathVariable(name = "categoryId") int categoryId) {
        return new ResponseEntity(categoryService.getById(categoryId), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PostMapping("/create")
    public ResponseEntity<Category> create(@RequestBody CategoryRequest categoryRequest) {
        Category category1 = categoryService.create(categoryRequest);
        if (category1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(category1, HttpStatus.OK);
    }

//    @PreAuthorize("")
    @PutMapping("update/{categoryId}")
    public ResponseEntity<Category> updateById(@PathVariable("categoryId") int categoryId, @Valid @RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryService.updateById(categoryId, categoryRequest), HttpStatus.OK);
    }

//    @PreAuthorize("")
    @DeleteMapping("delete/{categoryId}")
    public ResponseEntity<?> deleteById(@PathVariable("categoryId") int id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
