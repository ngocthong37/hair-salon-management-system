package com.hairsalon.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hairsalon.entity.*;
import com.hairsalon.respository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public ResponseEntity<Object> add(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        ReviewServiceHair review = new ReviewServiceHair();
        try {
            if (json == null || json.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject("ERROR", "Empty JSON", ""));
            }
            JsonNode jsonObjectAppointment = objectMapper.readTree(json);
            Integer customerId = jsonObjectAppointment.get("customerId") != null ?
                    Integer.parseInt(jsonObjectAppointment.get("customerId").asText()) : 1;
            Integer serviceId = jsonObjectAppointment.get("serviceId") != null ?
                    Integer.parseInt(jsonObjectAppointment.get("serviceId").asText()) : 1;
            Integer rating = jsonObjectAppointment.get("rating") != null ?
                    Integer.parseInt(jsonObjectAppointment.get("rating").asText()) : 1;
            String comment = jsonObjectAppointment.get("comment") != null ?
                    jsonObjectAppointment.get("comment").asText() : "";

            User customer = new User();
            customer.setId(customerId);

            ServiceHair serviceHair = new ServiceHair();
            serviceHair.setId(serviceId);
            review.setCustomer(customer);
            review.setServiceHair(serviceHair);
            review.setRatingValue(rating);
            review.setComment(comment);

            reviewRepository.save(review);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("OK", "Successfully", ""));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("ERROR", "An error occurred", e.getMessage()));
        }
    }

}
