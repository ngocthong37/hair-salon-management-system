package com.hairsalon.controller;

import com.hairsalon.entity.ResponseObject;
import com.hairsalon.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/promotion/")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;
    @PostMapping("add")
    ResponseEntity<ResponseObject> addPromotion(@RequestBody String json) {
        return promotionService.addPromotion(json);
    }
}
