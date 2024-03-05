package com.hairsalon.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.hairsalon.entity.*;
import com.hairsalon.model.*;
import com.hairsalon.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<ResponseObject> findAll() {
        List<Product> productList = new ArrayList<>();
        productList = productRepository.findAll();
        List<ProductModel> productModelList = productList.stream()
                .map(product -> {
                    ProductModel productModel = new ProductModel();
                    productModel.setId(product.getId());
                    productModel.setProductName(product.getName());
                    productModel.setDescription(product.getDescription());
                    productModel.setImageUrl(product.getImageUrl());
                    return productModel;
                })
                .collect(Collectors.toList());
        if (!productModelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", productModelList));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

    public ResponseEntity<ResponseObject> findAllByCateId(Integer categoryId) {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<Optional> productList = new ArrayList<>();
        productList = productRepository.findByCategoryId(categoryId);
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
            productRepository.save(product);
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
            Optional<Product> productOptional = productRepository.findById(id);

            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setId(id);
                product.setName(productName);
                Category category = new Category();
                category.setId(categoryId);
                product.setCategory(category);
                product.setImageUrl(imageUrl);
                product.setDescription(description);
                productRepository.save(product);
            }
            else {
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
