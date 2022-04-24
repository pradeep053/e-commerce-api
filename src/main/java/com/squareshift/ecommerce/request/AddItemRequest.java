package com.squareshift.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequest {

    @NotNull
    @JsonProperty("product_id")
    private long productID;

    @NotNull
    @Positive
    private int quantity;
}
