package com.hairsalon.controller;

import com.hairsalon.entity.ResponseObject;
import com.hairsalon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<ResponseObject> findAll() {
        return productService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findAllByCateId(@PathVariable Integer id) {
        return productService.findAllByCateId(id);
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addProduct(@RequestBody String json) {
        return productService.add(json);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateProduct(@RequestBody String json) {
        return productService.update(json);
    }

}
