package com.hairsalon.controller;


import com.hairsalon.entity.ResponseObject;
import com.hairsalon.service.CustomerService;
import com.hairsalon.service.OrderService;
import com.hairsalon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/v1/customer")
public class CustomerController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;


    @GetMapping("")
    public ResponseEntity<ResponseObject> findAllCustomer() {
        return customerService.findAll();
    }

    @PostMapping("/order")
    public ResponseEntity<ResponseObject> order(@RequestBody String json) {
        return orderService.order(json);
    }

}
