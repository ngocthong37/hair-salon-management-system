package com.hairsalon.controller;

import com.hairsalon.entity.*;
import com.hairsalon.service.ServiceHairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/")
public class ServiceController {

    @Autowired
    private ServiceHairService hairService;

    @GetMapping("services/findAll")
    public ResponseEntity<ResponseObject> getAllService() {
        return hairService.getAll();
    }

    @GetMapping(value = "management/services/{id}")
    public ResponseEntity<ResponseObject> getServiceById(@PathVariable Integer id) {
        return hairService.findById(id);
    }

    @GetMapping("services/search/{serviceName}")
    public ResponseEntity<ResponseObject> findServiceByName(@PathVariable String serviceName) {
        return hairService.findByServiceName(serviceName);
    }

    @PostMapping("management/add")
    public ResponseEntity<Object> addServiceHair(@RequestBody String json) {
        return hairService.add(json);
    }

    @PutMapping("management/update")
    public ResponseEntity<Object> updateServiceHair(@RequestBody String json) {
        return hairService.update(json);
    }

}
