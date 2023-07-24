package com.hairsalon.controller;

import com.hairsalon.entity.ResponseObject;
import com.hairsalon.service.CustomerService;
import com.hairsalon.service.OrderService;
import com.hairsalon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @GetMapping("users")
    public ResponseEntity<ResponseObject> findAllCustomer() {
        return userService.findAll();
    }

    @GetMapping("ordered")
    public ResponseEntity<ResponseObject> findAllOrder() {
        return orderService.findAll();
    }

    @GetMapping("ordered/{id}")
    public ResponseEntity<ResponseObject> findAllOrder(@PathVariable Integer id) {
        return orderService.findAllByStatusId(id);
    }



}
