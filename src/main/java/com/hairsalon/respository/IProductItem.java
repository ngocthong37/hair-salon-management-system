package com.hairsalon.respository;

import com.hairsalon.entity.ProductItem;
import com.hairsalon.model.ProductItemModel;

import java.util.List;

public interface IProductItem {
    ProductItemModel findById(Integer id);
    Integer add(ProductItem product);
    Integer update(ProductItem product);
    List<ProductItemModel> findAll();
}
