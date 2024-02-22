package com.hairsalon.controller;

import com.hairsalon.entity.ResponseObject;
import com.hairsalon.service.AppointmentService;
//import com.hairsalon.service.CustomerService;
import com.hairsalon.service.CartItemService;
import com.hairsalon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping(path = "/api/v1/")
public class CustomerController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartItemService cartItemService;

//    @Autowired
//    private CustomerService customerService;

    @Autowired
    private AppointmentService appointmentService;

//    @GetMapping("management/customer/findAll")
//    public ResponseEntity<ResponseObject> findAllCustomer() {
//        return customerService.findAll();
//    }

    @PostMapping("customer/order")
    public ResponseEntity<ResponseObject> order(@RequestBody String json) {
        return orderService.order(json);
    }

    @GetMapping("orders/{customerId}")
    public ResponseEntity<ResponseObject> findAllOrderByCustomerId(@PathVariable Integer customerId) {
        return orderService.findAllByCustomerId(customerId);
    }

    @GetMapping("appointments/{id}")
    ResponseEntity<ResponseObject> getAllByCustomerId(@PathVariable Integer id) {
        return appointmentService.getAllByCustomerId(id);
    }

    @PostMapping("customer/addToCart")
    public ResponseEntity<ResponseObject> addToCart(@RequestBody String json) {
        return cartItemService.addToCart(json);
    }

    @GetMapping("customer/findAllCartItems")
    public ResponseEntity<ResponseObject> findAll() {
        return cartItemService.findAll();
    }

    @DeleteMapping("customer/deleteCartItem/{id}")
    public ResponseEntity<ResponseObject> deleteCartItemById(@PathVariable Integer id) {
        return cartItemService.deleteCartItem(id);
    }

    @PutMapping("customer/updateQuantityCartItem")
    public ResponseEntity<ResponseObject> updateQuantityCartItem(@RequestBody String json) {
        return cartItemService.updateQuantityItem(json);
    }

}
