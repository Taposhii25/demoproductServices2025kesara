package com.scaler.demoproductservices2025kesara.controllerAdvices;

import com.scaler.demoproductservices2025kesara.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleproduct(ProductNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(),
                                    HttpStatus.NOT_FOUND);

    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<String> handleproduct(){
//        return new ResponseEntity<>("Product with this id doesnot exist",
//                HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler(AccessDeniedException.class)
//    public void Arrayindexoutofboundexception(){
//
//    }
//    @ExceptionHandler(Exception.class)
//    public void handleexception(Exception ex){
//
//    }
}
