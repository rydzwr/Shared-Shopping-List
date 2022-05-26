package com.rydzwr.sharedShoppingList.exceptions;

import com.rydzwr.sharedShoppingList.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RequestException extends RuntimeException
{
    public RequestException(String message)
    {
        super(message);
    }

    public RequestException(Throwable cause)
    {
        super(cause);
    }
}
