package com.rydzwr.sharedShoppingList.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler
{
    @ExceptionHandler(value = {IdNotFoundException.class})
    public ResponseEntity<Object> handleException(IdNotFoundException e)
    {
        HttpStatus badRequest = HttpStatus.NOT_FOUND;

        Exception exception = new Exception(
                e.getMessage(),
                e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exception, badRequest);
    }

    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<Object> handleException(InvalidInputException e)
    {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        Exception exception = new Exception(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exception, badRequest);
    }

    @ExceptionHandler(value = {UserAlreadyAssignedToHouseException.class})
    public ResponseEntity<Object> handleException(UserAlreadyAssignedToHouseException e)
    {
        HttpStatus badRequest = HttpStatus.NOT_FOUND;

        Exception exception = new Exception(
                e.getMessage(),
                e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exception, badRequest);
    }

    @ExceptionHandler(value = {UserNotAssignedToHouseException.class})
    public ResponseEntity<Object> handleException(UserNotAssignedToHouseException e)
    {
        HttpStatus badRequest = HttpStatus.NOT_FOUND;

        Exception exception = new Exception(
                e.getMessage(),
                e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exception, badRequest);
    }
}
