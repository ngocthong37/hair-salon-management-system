package com.hairsalon.respository;

import com.hairsalon.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Optional> findByCategoryId(Integer categoryId);
}