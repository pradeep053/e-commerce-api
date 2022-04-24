package com.squareshift.ecommerce.repository;

import com.squareshift.ecommerce.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartItemsRepository extends JpaRepository<CartItems,Integer> {

    Boolean existsByProductID(long productID);
   // int deleteByProductID(long productID);

    @Modifying
    @Query(value ="update CartItems c set c.quantity = ?1 where c.productID = ?2")
    int updateQuantity(long quantity,long productID);
}
