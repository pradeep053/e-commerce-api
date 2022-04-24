package com.squareshift.ecommerce.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData<T> {


  private String status;
  private  String message;

  public ResponseData() {}

  public ResponseData(String status,String message) {
    this.status=status;
    this.message=message;
  }
}
