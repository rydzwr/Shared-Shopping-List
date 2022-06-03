package com.rydzwr.sharedShoppingList.repository;

import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.User;

import java.util.Optional;

public interface HouseRepository
{
    Optional<House> findById(Integer id);

    boolean existsById(Integer id);

    House save(House entity);

    House getHouseByUsersContains(User user);

    House getHouseByPassword(String pass);

    void deleteById(int id);

    boolean existsByPassword(String pass);
}
