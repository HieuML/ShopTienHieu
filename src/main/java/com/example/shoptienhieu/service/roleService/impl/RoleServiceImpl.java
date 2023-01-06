package com.example.shoptienhieu.service.roleService.impl;

import com.example.shoptienhieu.constants.RoleName;
import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.entities.Role;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.RoleRepository;
import com.example.shoptienhieu.service.roleService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(int id) {
        return roleRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(TextStatus.ROLE_NOT_FOUND)
        );
    }

    @Override
    public Role getByName(String name) {
        Role role = null;
        switch (name){
            case "customer":
                role = roleRepository.findByName(RoleName.CUSTOMER);
                break;
            case "seller":
                role = roleRepository.findByName(RoleName.SELLER);
                break;
            case "admin":
                role = roleRepository.findByName(RoleName.ADMIN);
                break;
            default:
                throw new ResourceNotFoundException(TextStatus.ROLE_NOT_FOUND);
        }
        return role;
    }
}
