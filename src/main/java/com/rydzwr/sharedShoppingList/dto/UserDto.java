package com.rydzwr.sharedShoppingList.dto;

import com.rydzwr.sharedShoppingList.model.House;

public class UserDto
{
    private int id;
    private String name;
    private House house;

    public UserDto(int id, String name, House house)
    {
        this.id = id;
        this.name = name;
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

    public House getHouse()
    {
        return house;
    }
}
