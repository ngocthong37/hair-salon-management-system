package com.hairsalon.respository;

import com.hairsalon.entity.ProductItem;
import com.hairsalon.model.ProductItemModel;

public interface IProductItem {
    ProductItemModel findById(Integer id);
}
