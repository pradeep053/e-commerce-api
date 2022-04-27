package com.squareshift.ecommerce.repository;

import com.squareshift.ecommerce.entity.ShippingCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingCostRepository extends JpaRepository<ShippingCost,Float> {

    @Query(value ="select s.cost from ShippingCost s where s.minDistance<=?1 and s.maxDistance>=?1 and s.minWeight<=?2 and s.maxWeight>=?2")
    float getCost(float distance,float weight);

}
