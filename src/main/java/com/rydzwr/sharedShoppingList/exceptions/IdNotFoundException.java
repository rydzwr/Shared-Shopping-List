package com.rydzwr.sharedShoppingList.exceptions;

public class IdNotFoundException extends RuntimeException
{
    public IdNotFoundException(String message)
    {
        super(message);
    }

    public IdNotFoundException(Throwable cause)
    {
        super(cause);
    }
}
