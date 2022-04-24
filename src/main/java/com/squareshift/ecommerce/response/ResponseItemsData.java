package com.squareshift.ecommerce.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareshift.ecommerce.entity.CartItems;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseItemsData<T> {

    private String status;
    private String message;
    @JsonProperty("items")
    List<Items> allItems;

}