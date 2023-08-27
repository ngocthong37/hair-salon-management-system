package com.hairsalon.respository;

import com.hairsalon.model.OrderStatusModel;

public interface IOrderStatusRepository {
    OrderStatusModel findById(Integer id);
}
