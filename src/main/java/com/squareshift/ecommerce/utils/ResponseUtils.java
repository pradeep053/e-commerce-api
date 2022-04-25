package com.squareshift.ecommerce.utils;

import com.squareshift.ecommerce.constants.Constants;
import com.squareshift.ecommerce.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResponseUtils {

    /*public ResponseEntity<Object> getErrorResponses(Exception e, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseData<>(Constants.ERROR,e.getMessage()),
                httpStatus);
    }*/

    public ResponseEntity<Object> getErrorResponses(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseData<>(Constants.ERROR,message), httpStatus);
    }


}
