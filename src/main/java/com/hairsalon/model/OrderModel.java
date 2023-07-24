package com.hairsalon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderModel {
    private Integer id;
    private CustomerModel customerModel;
    private Integer totalPrice;
    private PaymentMethodModel paymentMethodModel;
    private OrderStatusModel orderStatusModel;
    private LocalDate orderDate;
}
