package com.rydzwr.sharedShoppingList.exceptions;

public class UserAlreadyAssignedToHouseException extends RuntimeException
{
    public UserAlreadyAssignedToHouseException(String message)
    {
        super(message);
    }

    public UserAlreadyAssignedToHouseException(Throwable cause)
    {
        super(cause);
    }
}