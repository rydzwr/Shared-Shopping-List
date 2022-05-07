package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.HouseRepository;

import java.util.List;

public class DbHouseService
{
    private final HouseRepository repository;

    public DbHouseService(HouseRepository repository)
    {
        this.repository = repository;
    }

    public List<User> getAllUsers(House house)
    {
        return house.getUsers();
    }

    public void addUser(User user, int houseId)
    {
        repository.findById(houseId).get().getUsers().add(user);
        repository.save(repository.findById(houseId).get());
    }
}
