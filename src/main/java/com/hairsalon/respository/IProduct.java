package com.hairsalon.respository;

import com.hairsalon.entity.Order;
import com.hairsalon.entity.OrderItem;
import com.hairsalon.entity.Product;
import com.hairsalon.model.OrderModel;
import com.hairsalon.model.ProductModel;

import java.util.List;

public interface IProduct {
    List<ProductModel> findAll();
    List<ProductModel> findAllByCategoryId(Integer cateId);
    Product findById(Integer id);
    Integer add(Product product);
    Integer update(Product product);

}
