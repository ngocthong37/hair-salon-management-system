package com.hairsalon.respository;

import com.hairsalon.model.OrderModel;

public interface IOrder {
    OrderModel findOrderById(Integer id);
}
