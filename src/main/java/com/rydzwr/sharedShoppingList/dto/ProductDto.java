package com.rydzwr.sharedShoppingList.dto;

import com.rydzwr.sharedShoppingList.model.ProductGroup;

public class ProductDto
{
    private int id;
    private String name;
    private String description;
    private boolean important;
    private boolean bought;
    private ProductGroup group;

    public ProductDto(int id, String name, String description, boolean important, boolean bought, ProductGroup group)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.important = important;
        this.bought = bought;
        this.group = group;
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

    public ProductGroup getGroup()
    {
        return group;
    }
}
