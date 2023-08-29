package com.hairsalon.controller;

import com.hairsalon.entity.ResponseObject;
import com.hairsalon.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/management/")
public class RevenueController {

    @Autowired
    RevenueService revenueService;

    @GetMapping("revenueFromService")
    public ResponseEntity<ResponseObject> getTotalRevenueFromService() {
        return revenueService.getRevenueFromService();
    }

    @GetMapping("revenueFromProduct")
    public ResponseEntity<ResponseObject> getTotalRevenueFromProduct() {
        return revenueService.getRevenueFromProduct();
    }

    @GetMapping("revenueService/{year}")
    public ResponseEntity<ResponseObject> getTotalRevenueFromServiceByYear(@PathVariable Integer year) {
        return revenueService.getRevenueFromServiceByYear(year);
    }

    @GetMapping("revenueService/{year}/{month}")
    public ResponseEntity<ResponseObject> getTotalRevenueFromServiceByYear(@PathVariable Integer year, @PathVariable Integer month) {
        return revenueService.getRevenueFromServiceByMonth(year, month);
    }


}
