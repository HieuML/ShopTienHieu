package com.example.shoptienhieu.service.roleService;

import com.example.shoptienhieu.entities.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAll();
    public Role getById(int id);
    public Role getByName(String name);
}
