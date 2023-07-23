package com.hairsalon.respository;

import com.hairsalon.model.OrderStatusModel;

public interface IOrderStatus {
    OrderStatusModel findById(Integer id);
}
