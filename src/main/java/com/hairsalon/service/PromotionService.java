package com.hairsalon.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hairsalon.entity.*;
import com.hairsalon.respository.PromotionDetailRepository;
import com.hairsalon.respository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PromotionService {
    @Autowired
    PromotionRepository promotionRepository;
    @Autowired
    PromotionDetailRepository promotionDetailRepository;

    public ResponseEntity<ResponseObject> addPromotion(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (json == null || json.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject("ERROR", "Empty JSON", ""));
            }
            JsonNode jsonObjectPromotion = objectMapper.readTree(json);
            String promotionName = jsonObjectPromotion.get("promotionName") != null ?
                    jsonObjectPromotion.get("promotionName").asText() : "";
            String startDateStr = jsonObjectPromotion.get("startDate") != null ?
                    jsonObjectPromotion.get("startDate").asText() : "";
            String endDateStr = jsonObjectPromotion.get("endDate") != null ?
                    jsonObjectPromotion.get("endDate").asText() : "";
            Integer discountPercent = jsonObjectPromotion.get("discountPercent") != null ?
                    jsonObjectPromotion.get("discountPercent").asInt() : 0;
            Integer productItemId = jsonObjectPromotion.get("productItemId") != null ?
                    jsonObjectPromotion.get("productItemId").asInt() : 0;
            Integer userId = jsonObjectPromotion.get("userId") != null ?
                    jsonObjectPromotion.get("userId").asInt() : 0;
            Promotion promotion = new Promotion();
            promotion.setPromotionName(promotionName);
            LocalDate startDate = null;
            LocalDate endDate = null;
            if (!startDateStr.isEmpty()) {
                startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);
            }
            if (!endDateStr.isEmpty()) {
                endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_DATE);
            }
            promotion.setStartDate(startDate);
            promotion.setEndDate(endDate);
            User user = new User();
            user.setId(userId);
            promotion.setUser(user);
            Promotion savePromotion = promotionRepository.save(promotion);
            if (savePromotion.getId() != null) {
                ProductItem productItem = new ProductItem();
                productItem.setId(productItemId);
                PromotionDetail promotionDetail = new PromotionDetail();
                promotionDetail.setPromotion(savePromotion);
                promotionDetail.setDiscountPercent(discountPercent);
                promotionDetail.setProductItem(productItem);
                PromotionDetail savePromotionDetail = promotionDetailRepository.save(promotionDetail);
                if (savePromotionDetail.getId() != null) {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseObject("OK", "Successfully", ""));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("ERROR", "An error occurred", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("ERROR", "Can not add to cart", ""));
    }
}
