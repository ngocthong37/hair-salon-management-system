package com.hairsalon.respository;

import com.hairsalon.entity.OrderProduct;
import com.hairsalon.entity.Product;
import com.hairsalon.model.OrderProductModel;
import com.hairsalon.model.ProductModel;

import java.util.List;

public interface IProduct {
    List<ProductModel> findAll();
    List<ProductModel> findAllByCategoryId(Integer cateId);
    Product findById(Integer id);
    Integer insert(OrderProduct orderProduct);
    OrderProductModel findOrderById(Integer id);


}
