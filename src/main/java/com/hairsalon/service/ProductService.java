package com.hairsalon.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
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

    public ResponseEntity<Object> add(String json) {
        JsonNode jsonNode;
        JsonMapper jsonMapper = new JsonMapper();
        try {
            jsonNode = jsonMapper.readTree(json);
            String productName = jsonNode.get("productName") != null ? jsonNode.get("productName").asText() : "";
            String description = jsonNode.get("description") != null ? jsonNode.get("description").asText() : "";
            String imageUrl = jsonNode.get("imageUrl") != null ? jsonNode.get("imageUrl").asText() : "";
            Integer categoryId = jsonNode.get("categoryId") != null ? jsonNode.get("categoryId").asInt() : -1;
            Product product = new Product();
            product.setName(productName);
            Category category = new Category();
            category.setId(categoryId);
            product.setCategory(category);
            product.setImageUrl(imageUrl);
            product.setDescription(description);
            if (productImp.add(product) < 0) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ERROR", "Have error when add product", ""));
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Error", e.getMessage(), ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", ""));
    }

    public ResponseEntity<Object> update(String json) {
        JsonNode jsonNode;
        JsonMapper jsonMapper = new JsonMapper();
        try {
            jsonNode = jsonMapper.readTree(json);
            Integer id = jsonNode.get("id") != null ? jsonNode.get("id").asInt() : -1;
            String productName = jsonNode.get("productName") != null ? jsonNode.get("productName").asText() : "";
            String description = jsonNode.get("description") != null ? jsonNode.get("description").asText() : "";
            String imageUrl = jsonNode.get("imageUrl") != null ? jsonNode.get("imageUrl").asText() : "";
            Integer categoryId = jsonNode.get("categoryId") != null ? jsonNode.get("categoryId").asInt() : -1;
            Product product = new Product();
            product.setId(id);
            product.setName(productName);
            Category category = new Category();
            category.setId(categoryId);
            product.setCategory(category);
            product.setImageUrl(imageUrl);
            product.setDescription(description);
            if (productImp.update(product) < 0) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ERROR", "Have error when update product", ""));
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Error", e.getMessage(), ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", ""));
    }



}
