package com.hairsalon.respository;


import com.hairsalon.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {
    @Query(value = "SELECT S FROM ProductItem S WHERE S.productItemName LIKE CONCAT('%', :productName, '%')")
    List<ProductItem> findByProductName(@Param("productName") String productName);

}
