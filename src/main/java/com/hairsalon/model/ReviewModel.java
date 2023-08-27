package com.hairsalon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReviewModel {
    private Integer id;
    private CustomerModel customerModel;
    private HairServiceModel hairServiceModel;
    private Integer rating;
    private String comment;
}
