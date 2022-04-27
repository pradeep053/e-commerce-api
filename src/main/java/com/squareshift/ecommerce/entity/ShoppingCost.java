package com.squareshift.ecommerce.entity;

import com.squareshift.ecommerce.constants.Constants;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= Constants.SHOPPING_COST)
public class ShoppingCost {

    @Id
    @Column(name = Constants.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = Constants.MIN_DISTANCE)
   private float minDistance;

    @Column(name = Constants.MAX_DISTANCE)
   private float maxDistance;

    @Column(name = Constants.MIN_WEIGHT)
   private float minWeight;

    @Column(name = Constants.MAX_WEIGHT)
   private float maxWeight;

    @Column(name = Constants.COST)
   private float cost;
}
