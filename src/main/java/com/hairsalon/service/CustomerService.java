//package com.hairsalon.service;
//
//import com.hairsalon.entity.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//
//@Service
//public class CustomerService {
//    @Autowired
//    CustomerRepository customerRepository;
//
//    public ResponseEntity<ResponseObject> findAll() {
//        Map<String, Object> results = new TreeMap<String, Object>();
//        List<Customer> customerList = new ArrayList<>();
//        customerList = customerRepository.findAll();
//        results.put("customerList", customerList);
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
//    }
//
//}
