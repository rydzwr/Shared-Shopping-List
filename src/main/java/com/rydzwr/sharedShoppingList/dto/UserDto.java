package com.rydzwr.sharedShoppingList.dto;

import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.ProductGroup;

public class UserDto
{
    private int id;
    private String name;
    private ProductGroup usersGroup;
    private House house;

    public UserDto(int id, String name, ProductGroup usersGroup, House house)
    {
        this.id = id;
        this.name = name;
        this.usersGroup = usersGroup;
        this.house = house;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public ProductGroup getUsersGroup()
    {
        return usersGroup;
    }

    public House getHouse()
    {
        return house;
    }
}
