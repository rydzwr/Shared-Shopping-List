package com.rydzwr.sharedShoppingList.dto;

import com.rydzwr.sharedShoppingList.model.User;

import java.util.List;

public class HouseDto
{
    private int id;
    private String password;
    private List<User> users;

    public HouseDto(int id, String password, List<User> users)
    {
        this.id = id;
        this.password = password;
        this.users = users;
    }

    public int getId()
    {
        return id;
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
