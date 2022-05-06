package com.rydzwr.sharedShoppingList.repository;

import com.rydzwr.sharedShoppingList.model.House;

import java.util.List;
import java.util.Optional;

public interface HouseRepository
{
    List<House> findAll();

    Optional<House> findById(Integer id);

    boolean existsById(Integer id);

    House save(House entity);
}
