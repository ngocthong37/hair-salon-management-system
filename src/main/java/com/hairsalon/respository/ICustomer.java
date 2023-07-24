package com.hairsalon.respository;

import com.hairsalon.entity.Order;
import com.hairsalon.entity.OrderItem;
import com.hairsalon.model.CustomerModel;

import java.util.List;

public interface ICustomer {
    List<CustomerModel> findAll();
    CustomerModel findById(Integer id);
    Integer insert(Order order, List<OrderItem> orderItems);
}
