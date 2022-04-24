package com.squareshift.ecommerce.entity;


import com.squareshift.ecommerce.constants.Constants;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name=Constants.CART_ITEMS)
public class CartItems {


    @Id
    @Column(name = Constants.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Constants.PRODUCT_ID)
    private long productID;

    @Column(name = Constants.QUANTITY)
    private long quantity;
}
