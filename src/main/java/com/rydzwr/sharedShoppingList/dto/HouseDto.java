package com.rydzwr.sharedShoppingList.dto;

import com.rydzwr.sharedShoppingList.model.User;

import java.util.List;

public class HouseDto
{
    private int id;
    private String name;
    private String password;
    private List<User> users;

    public HouseDto(int id, String name, String password, List<User> users)
    {
        this.id = id;
        this.name = name;
        this.password = password;
        this.users = users;
    }

    public HouseDto(String name)
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

    public String getPassword()
    {
        return password;
    }

    public List<User> getUsers()
    {
        return users;
    }
}
