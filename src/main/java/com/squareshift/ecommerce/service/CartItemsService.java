package com.squareshift.ecommerce.service;

import com.squareshift.ecommerce.dto.CartItemsAddResponseDto;
import com.squareshift.ecommerce.request.AddItemRequest;
import com.squareshift.ecommerce.request.DeleteItemRequest;
import org.springframework.http.ResponseEntity;

public interface CartItemsService {
    ResponseEntity<Object> addCartItem(AddItemRequest addItemRequest);
    ResponseEntity<Object> getCartItem();
    ResponseEntity<Object> deleteCartItem(DeleteItemRequest deleteItemRequest);
    ResponseEntity<Object> getTotalValue(long postalCode);


}
