package com.example.myblog.dao;

import com.example.myblog.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String theRoleName);

}
