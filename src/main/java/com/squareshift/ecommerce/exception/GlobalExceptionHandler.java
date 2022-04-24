package com.squareshift.ecommerce.exception;

import com.squareshift.ecommerce.utils.ResponseUtils;
import feign.FeignException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ResponseUtils responseUtils;

    @ExceptionHandler(FeignException.class)
    public String handleFeignStatusException(FeignException e, HttpServletResponse response) throws JSONException {
        response.setStatus(e.status());
        return new JSONObject(e.contentUTF8()).toString(1);
    }


    /**
     * Handle errors related to resource not found
     * @param ex
     * @param response
     * @return
     */
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException ex, HttpServletResponse response) {
        return responseUtils.getErrorResponses(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity handleCustomException(CustomException ex, HttpServletResponse response) {
        return responseUtils.getErrorResponses(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
