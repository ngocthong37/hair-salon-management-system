package com.hairsalon.service;

import com.hairsalon.entity.ResponseObject;
import com.hairsalon.entity.User;
import com.hairsalon.model.UserModel;
import com.hairsalon.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<ResponseObject> findAll() {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<User> userList = new ArrayList<>();
        userList = userRepository.findAll();
        results.put("userList", userList);
        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }


}
