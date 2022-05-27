package com.rydzwr.sharedShoppingList.exceptions;

public class InvalidInputException extends RuntimeException
{
    public InvalidInputException(String message)
    {
        super(message);
    }

    public InvalidInputException(Throwable cause)
    {
        super(cause);
    }
}
