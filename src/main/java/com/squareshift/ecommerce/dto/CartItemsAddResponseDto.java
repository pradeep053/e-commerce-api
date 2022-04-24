package com.squareshift.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CartItemsAddResponseDto {

    private String status;
    private String message;
}
