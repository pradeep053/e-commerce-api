package com.squareshift.ecommerce.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface Constants {
    String CART_ITEMS="cart_items";
    String ID="id";
    String PRODUCT_ID="product_id";
    String QUANTITY="quantity";

    String SHOPPING_COST="shipping_cost";
    String MIN_DISTANCE="min_distance";
    String MAX_DISTANCE="max_distance";
    String MIN_WEIGHT="min_weight";
    String MAX_WEIGHT="max_weight";
    String COST="cost";


    String INVALID_PRODUCT_ID ="Invalid product id";
    String NULL_REQUEST="Request is null";
    String INVALID_QUANTITY="Invalid quantity";
    String BAD_REQUEST="Error state. Bad request.";
  //  String NULL_PRODUCT_ID="Product id is null";

    String ITEM_ADDED="Item has been added to cart";
    String ITEM_DELETED="All items have been removed from the cart !";
    String CART_EMPTY="Card is empty";
    String CART_DATA="Item available in the cart";
    String EMPTY_CART="empty_cart";
    String INVALID_POSTAL="Invalid postal code, valid ones are 465535 to 465545.";
    String ERROR="error";
    String SUCCESS="success";
    String TOTAL_AMOUNT="Total value of your shopping cart is - ";

    float AMOUNT_MAP[][]={{12,15,20,50,100,220},
            {14,18,24,55,110,250},
            {16,25,30,80,130,270},
            {21,35,50,90,150,300}};

}
