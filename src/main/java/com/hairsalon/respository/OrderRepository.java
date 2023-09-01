package com.hairsalon.respository;

import com.hairsalon.entity.Order;
import com.hairsalon.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
