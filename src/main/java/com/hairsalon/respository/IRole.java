package com.hairsalon.respository;

import com.hairsalon.model.RoleDetailModel;

import java.util.List;

public interface IRole {
    RoleDetailModel findRoleDetailsByRoleId(Integer roleId);
    List<Integer> findAllRoleIdByUserId(Integer userId);
}
