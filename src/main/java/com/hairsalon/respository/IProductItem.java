package com.hairsalon.respository;

import com.hairsalon.entity.ProductItem;
import com.hairsalon.model.ProductItemModel;

import java.util.List;

public interface IProductItem {
    ProductItemModel findById(Integer id);
    List<ProductItemModel> findByProductItemName(String productItemName);
    Integer add(ProductItem product);
    Integer update(ProductItem product);
    List<ProductItemModel> findAll();

}
