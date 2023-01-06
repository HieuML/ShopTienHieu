package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.entities.Role;
import com.example.shoptienhieu.service.roleService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("findAll")
    public ResponseEntity<List<Role>> getAll() {
        List<Role> roles = roleService.getAll();
        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Role> getByRoleId(@PathVariable("id") int id) {
        Role role = roleService.getById(id);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable(name = "name") String name) {
        return new ResponseEntity(roleService.getByName(name), HttpStatus.OK);
    }
}
