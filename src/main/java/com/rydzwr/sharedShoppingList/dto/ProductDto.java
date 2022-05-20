package com.rydzwr.sharedShoppingList.dto;

import com.rydzwr.sharedShoppingList.model.User;

public class ProductDto
{
    private int id;
    private String name;
    private String description;
    private boolean important;
    private boolean bought;

    public ProductDto(int id, String name, String description, boolean important, boolean bought)
    {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription()
    {
        return description;
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
