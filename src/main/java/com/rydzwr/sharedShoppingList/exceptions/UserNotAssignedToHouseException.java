package com.rydzwr.sharedShoppingList.exceptions;

public class UserNotAssignedToHouseException extends RuntimeException
{
    public UserNotAssignedToHouseException(String message)
    {
        super(message);
    }

    public UserNotAssignedToHouseException(Throwable cause)
    {
        super(cause);
    }
}