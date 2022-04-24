package com.squareshift.ecommerce.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Items {

    @JsonProperty("product_id")
    public long productId;
    public String description;
    public long quantity;

    public Items(){};

    public Items(long productId,String description,long quantity){
        this.productId=productId;
        this.description=description;
        this.quantity=quantity;
    }

}
