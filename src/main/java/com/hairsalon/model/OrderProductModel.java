package com.hairsalon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderProductModel {
    private Integer id;
    private CustomerModel customerModel;
    private ProductItemModel productItemModel;
    private Integer quantity;
    private OrderStatusModel orderStatusModel;
}
