package com.hairsalon.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.hairsalon.entity.Product;
import com.hairsalon.entity.ProductItem;
import com.hairsalon.entity.ResponseObject;
import com.hairsalon.model.ProductItemModel;
import com.hairsalon.respository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductItemService {
    @Autowired
    private ProductItemRepository productItemRepository;

    public ResponseEntity<ResponseObject> findAll() {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<ProductItem> productItemModelList = new ArrayList<>();
        productItemModelList = productItemRepository.findAll();
        results.put("productItemList", productItemModelList);
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
            Double price = jsonNode.get("price") != null ? jsonNode.get("price").asDouble() : null;
            Integer quantity = jsonNode.get("quantity") != null ? jsonNode.get("quantity").asInt() : -1;
            Integer productId = jsonNode.get("productId") != null ? jsonNode.get("productId").asInt() : -1;
            Integer warrantyTime = jsonNode.get("warrantyTime") != null ? jsonNode.get("warrantyTime").asInt() : -1;
            String status = jsonNode.get("status") != null ? jsonNode.get("status").asText() : "";

            ProductItem productItem = new ProductItem();
            productItem.setProductItemName(productName);
            Product product = new Product();
            product.setId(productId);
            productItem.setProduct(product);
            productItem.setPrice(price);
            productItem.setStatus(status);
            productItem.setQuantityInStock(quantity);
            productItem.setStatus(status);
            productItem.setWarrantyTime(warrantyTime);

            ProductItem saveProduct = productItemRepository.save(productItem);
            if (saveProduct.getId() == null)
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ERROR", "Have error when add product item", ""));

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
            String productName = jsonNode.get("productName") != null ? jsonNode.get("productName").asText() : "";
            Double price = jsonNode.get("price") != null ? jsonNode.get("price").asDouble() : null;
            Integer quantity = jsonNode.get("quantity") != null ? jsonNode.get("quantity").asInt() : -1;
            Integer productItemId = jsonNode.get("productItemId") != null ? jsonNode.get("productItemId").asInt() : -1;
            Integer warrantyTime = jsonNode.get("warrantyTime") != null ? jsonNode.get("warrantyTime").asInt() : -1;
            String status = jsonNode.get("status") != null ? jsonNode.get("status").asText() : "";
            Optional<ProductItem> productItemOptional = productItemRepository.findById(productItemId);

            if (productItemOptional.isPresent()) {
                ProductItem productItem = productItemOptional.get();
                productItem.setProductItemName(productName);
                productItem.setPrice(price);
                productItem.setWarrantyTime(warrantyTime);
                productItem.setQuantityInStock(quantity);
                productItem.setStatus(status);
                productItemRepository.save(productItem);
            }
            else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ERROR", "Have error when update product item", ""));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Error", e.getMessage(), ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", ""));
    }

    public ResponseEntity<ResponseObject> findByProductItemName(String productItemName) {
        try {
            Map<String, Object> results = new TreeMap<String, Object>();
            List<ProductItem> productItemList = productItemRepository.findByProductName(productItemName);
            results.put("productItemList", productItemList);
            if (results.size() > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ERROR", "Have error", ""));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("ERROR", "Have error:", e.getMessage()));
        }
    }

}
