package com.blz.bankdetails.exception.exceptionhandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.blz.bankdetails.exception.CustomNotFoundException;
import com.blz.bankdetails.util.Response;


@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<Response> handleHiringException(CustomNotFoundException he){
        Response response=new Response();
        response.setErrorCode(400);
        response.setMessage(he.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}