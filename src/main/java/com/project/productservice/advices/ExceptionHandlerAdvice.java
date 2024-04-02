package com.project.productservice.advices;

import com.project.productservice.dtos.ExceptionDTO;
import com.project.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    // This method will handle ProductNotFoundException
    // Controller advice is used to handle exceptions globally
    //
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setId(productNotFoundException.getProductId());
        exceptionDTO.setMessage(productNotFoundException.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
