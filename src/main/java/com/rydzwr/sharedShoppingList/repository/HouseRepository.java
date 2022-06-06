package com.rydzwr.sharedShoppingList.repository;

import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.User;

import java.util.List;
import java.util.Optional;

public interface HouseRepository
{
    Optional<House> findById(Integer id);

    List<House> findAll();

    boolean existsById(Integer id);

    void deleteById(int houseId);

    House save(House entity);

    House getHouseByUsersContains(User user);

    House getHouseByPassword(String pass);

    boolean existsByPassword(String pass);
}
