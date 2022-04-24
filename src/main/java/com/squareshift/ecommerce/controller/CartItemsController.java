package com.squareshift.ecommerce.controller;

import com.squareshift.ecommerce.constants.Constants;
import com.squareshift.ecommerce.exception.CustomException;
import com.squareshift.ecommerce.request.AddItemRequest;
import com.squareshift.ecommerce.request.DeleteItemRequest;
import com.squareshift.ecommerce.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/api")
public class CartItemsController {

    @Autowired
    private CartItemsService cartItemsService;

    @PutMapping(value = "/v1/cart/addItems")
    public ResponseEntity<Object> addCartItems(@Valid @RequestBody AddItemRequest addItemRequest) throws Exception{

        if(addItemRequest==null){
            throw new CustomException(Constants.NULL_REQUEST);
        }
        if(addItemRequest.getQuantity()<1){
            throw new CustomException(Constants.INVALID_QUANTITY);
        }
       return cartItemsService.addCartItem(addItemRequest);
    }

    @GetMapping(value = "/v1/cart/getAllItems")
    public ResponseEntity<Object> getCartItems() throws Exception{
        return cartItemsService.getCartItem();
    }

    @DeleteMapping(value = "/v1/cart/emptyCart")
    public ResponseEntity<Object> deleteCartItems(@Valid @RequestBody DeleteItemRequest deleteItemRequest) throws Exception{
        if(deleteItemRequest==null){
            throw new CustomException(Constants.NULL_REQUEST);
        }
        if(deleteItemRequest.getAction().equals(Constants.EMPTY_CART)){
            return cartItemsService.deleteCartItem(deleteItemRequest);
        }else{
            throw new CustomException(Constants.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/v1/cart/getTotalValue")
    public ResponseEntity<Object> getTotalValue(@RequestParam(name = "shipping_postal_code", required = true) Long postalCode) throws Exception{
        return cartItemsService.getTotalValue(postalCode);
    }




}
