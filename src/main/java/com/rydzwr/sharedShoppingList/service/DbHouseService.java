package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.HouseRepository;
import com.rydzwr.sharedShoppingList.repository.UserRepository;

import java.util.List;
import java.util.Random;

public class DbHouseService
{
    private final HouseRepository repository;
    private final UserRepository userRepository;

    public DbHouseService(HouseRepository repository, UserRepository userRepository)
    {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public String getHouseName(int id)
    {
        return repository.findById(id).get().getName();
    }

    public List<User> getUsers(int id)
    {
        return repository.findById(id).get().getUsers();
    }

    public String getPassword(int id)
    {
        // TO DO

        // 2 min validation

        Random random = new Random();
        House house = repository.findById(id).get();
        String password = String.format("%04d", random.nextInt(10000));
        house.setPassword(password);
        return house.getPassword();
    }

    public void addUser(int id, User user)
    {
        House house = repository.findById(id).get();
        house.getUsers().add(user);
        repository.save(house);
    }

    // Removes all bought products from all users
    public void clearHouse(int id)
    {
        House house = repository.findById(id).get();
        List<User> users = house.getUsers();
        for (User user : users)
        {
            List<Product> products = user.getProductsList();

            for (int i = 0; i < products.size(); i++)
            {
                if (products.get(i).isBought() == true)
                    products.remove(i);
            }

            userRepository.save(user);
        }

        repository.save(house);
    }

    public void removeUser(int houseId, int userId)
    {
        House house = repository.findById(houseId).get();

        List<User> users = house.getUsers();

        for (User user : users)
        {
            if (user.getId() == userId)
                users.remove(user);
        }

        repository.save(house);
    }
}
