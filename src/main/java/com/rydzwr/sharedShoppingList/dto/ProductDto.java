package com.rydzwr.sharedShoppingList.dto;

import com.rydzwr.sharedShoppingList.model.User;

public class ProductDto
{
    private int id;
    private String name;
    private boolean important;
    private boolean bought;

    public ProductDto(int id, String name, boolean important, boolean bought)
    {
        this.id = id;
        this.name = name;
        this.important = important;
        this.bought = bought;
    }


    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public boolean isImportant()
    {
        return important;
    }

    public boolean isBought()
    {
        return bought;
    }

}
