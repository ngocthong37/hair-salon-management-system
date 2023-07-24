package com.hairsalon.respository;

import com.hairsalon.entity.Order;
import com.hairsalon.model.OrderModel;

import java.util.List;

public interface IOrder {
    OrderModel findOrderModelById(Integer id);
    Order findOrderById(Integer id);
    List<OrderModel> findAll();
    List<OrderModel> findAllByStatusId(Integer id);
    List<OrderModel> findAllByCustomerId(Integer id);
    Integer updateStatusOrder(Order order);


}
