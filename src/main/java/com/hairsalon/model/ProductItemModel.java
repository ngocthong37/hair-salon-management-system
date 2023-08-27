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

public class ProductItemModel {
    private Integer id;
    private String productName;
    private Double price;
    private Integer quantityInStock;
    private Integer warrantyTime;
    private String status;
    private ProductModel productModel;
}
