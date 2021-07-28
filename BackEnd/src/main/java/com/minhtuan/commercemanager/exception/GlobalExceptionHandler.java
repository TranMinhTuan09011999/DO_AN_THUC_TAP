package com.minhtuan.commercemanager.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistedException.class)
    public ResponseEntity<Object> resourceAlreadyExists(EmailAlreadyExistedException ex) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        ResponseError err = new ResponseError(LocalDateTime.now(), "Duplicate Data", details);

        return ResponseMapper.errorToEntity(err, HttpStatus.BAD_REQUEST);
    }

    // handleMethodArgumentTypeMismatch : triggers when a parameter's type does not match
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request)
    {

        List<String> details = new ArrayList<String>();
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField()+ " : " +error.getDefaultMessage())
                .collect(Collectors.toList());

        ResponseError err = new ResponseError(LocalDateTime.now(), "Validation Errors" ,details);

        return ResponseMapper.errorToEntity(err,HttpStatus.BAD_REQUEST);

    }
}
