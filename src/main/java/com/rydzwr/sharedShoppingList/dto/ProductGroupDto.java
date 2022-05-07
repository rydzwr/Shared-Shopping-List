package com.rydzwr.sharedShoppingList.dto;

import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.User;

import java.util.List;

public class ProductGroupDto
{
    private int id;
    private String name;
    private List<Product> products;
    private User user;

    public ProductGroupDto(int id, String name, List<Product> products, User user)
    {
        this.id = id;
        this.name = name;
        this.products = products;
        this.user = user;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public List<Product> getProducts()
    {
        return products;
    }

    public User getUser()
    {
        return user;
    }
}
