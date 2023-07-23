package com.hairsalon.respository;

import com.hairsalon.entity.Permission;
import com.hairsalon.model.PermissionModel;

import java.util.List;

public interface IPermission {
    List<Permission> findAll();
}
