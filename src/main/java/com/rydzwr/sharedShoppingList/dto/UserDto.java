package com.rydzwr.sharedShoppingList.dto;

import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.Product;

import java.util.List;

public class UserDto
{
    private int id;
    private String name;
    private List<Product> productsList;

    public UserDto(int id, String name, List<Product> productsList)
    {
        this.id = id;
        this.name = name;
        this.productsList = productsList;
    }

    public UserDto(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public List<Product> getProductsList()
    {
        return productsList;
    }
}
