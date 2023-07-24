package com.hairsalon.service;

import com.hairsalon.entity.*;
import com.hairsalon.model.*;
import com.hairsalon.respository.imp.ProductImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class ProductService {
    @Autowired
    ProductImp productImp;

    public ResponseEntity<ResponseObject> findAll() {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<ProductModel> productList = new ArrayList<>();
        productList = productImp.findAll();
        results.put("productList", productList);
        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

    public ResponseEntity<ResponseObject> findAllByCateId(Integer id) {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<ProductModel> productList = new ArrayList<>();
        productList = productImp.findAllByCategoryId(id);
        results.put("productList", productList);
        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

}
