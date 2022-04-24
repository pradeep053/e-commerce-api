package com.squareshift.ecommerce.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaResponse {

  private String displayMessage;

  public MetaResponse() {}

  public MetaResponse(String displayMessage) {
    this.displayMessage = displayMessage;
  }
}
