package com.rydzwr.sharedShoppingList.dto;

import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.Product;

import java.util.List;
import java.util.Optional;

public class UserDto
{
    private int id;
    private Integer houseId;
    private String name;

    public UserDto(int id, Integer houseId, String name)
    {
        this.id = id;
        this.houseId = houseId;
        this.name = name;
    }

    public UserDto(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public Integer getHouseId()
    {
        return houseId;
    }

    public String getName()
    {
        return name;
    }

}
