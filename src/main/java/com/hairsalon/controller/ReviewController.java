package com.hairsalon.controller;


import com.hairsalon.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/")
public class ReviewController {

    @Autowired
    ReviewService reviewService;


    @PostMapping("comment")
    public ResponseEntity<Object> addComment(@RequestBody String json) {
        return reviewService.add(json);
    }
}
