package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.entities.User;
import com.example.shoptienhieu.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping(path = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("findAll")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping("findById")
    public ResponseEntity<User> getUserById(@RequestParam(name = "userId") int userId){
        return ResponseEntity.ok(userService.getById(userId));
    }

    @PostMapping("findByFirstName")
    public ResponseEntity<List<User>> findByFirstName(@RequestParam(name = "firstName") String firstName){
        return ResponseEntity.ok(userService.findByFirstName(firstName));
    }



}
