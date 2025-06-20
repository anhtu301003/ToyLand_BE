package com.toyland.cart_service.repository;

import com.toyland.cart_service.entity.Cart;
import com.toyland.cart_service.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {
}
