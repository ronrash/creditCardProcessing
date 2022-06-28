package com.sapients.creditcardProcessing.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errorMap = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errorMap.put(fieldError.getField(),
                        fieldError.getDefaultMessage()));
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidCreditCardException.class)
    public Map<String, String> handleInvalidCreditCard(InvalidCreditCardException invalidCreditCardException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", invalidCreditCardException.getMessage());
        return errorMap;
    }
}
