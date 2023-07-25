package com.hairsalon.service;

import com.hairsalon.entity.ResponseObject;
import com.hairsalon.model.CustomerModel;
import com.hairsalon.respository.imp.RevenueImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RevenueService {
    @Autowired
    RevenueImp revenueImp;

    public ResponseEntity<ResponseObject> getRevenueFromService() {
        Map<String, Object> results = new TreeMap<String, Object>();
        Double price = revenueImp.getRevenueFromService();
        results.put("totalMoney", price);
        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

    public ResponseEntity<ResponseObject> getRevenueFromProduct() {
        Map<String, Object> results = new TreeMap<String, Object>();
        Double price = revenueImp.getRevenueFromProduct();
        results.put("totalMoney", price);
        results.size();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
    }

}
