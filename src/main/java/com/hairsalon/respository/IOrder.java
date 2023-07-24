package com.hairsalon.respository;

import com.hairsalon.model.OrderModel;

import java.util.List;

public interface IOrder {
    OrderModel findOrderById(Integer id);
    List<OrderModel> findAll();
    List<OrderModel> findAllByStatusId(Integer id);
}
