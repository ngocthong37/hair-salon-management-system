package com.hairsalon.respository;

import com.hairsalon.entity.User;
import com.hairsalon.model.UserModel;

import java.util.List;

public interface IUserRepository {
    User add(User user);
    User findById(Integer id);
    UserModel findByUserName(String userName);
    User loadUserByUserName(String userName);
    List<UserModel> findAll();
    Boolean checkExistingUserByUsername(String username);
}
