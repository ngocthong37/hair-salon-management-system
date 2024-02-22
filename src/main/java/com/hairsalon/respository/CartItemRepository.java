package com.hairsalon.respository;

import com.hairsalon.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    @Query("SELECT c FROM CartItem c WHERE c.cart.id = :cartId")
    List<CartItem> findAllCartItemByCartId(Integer cartId);
}
