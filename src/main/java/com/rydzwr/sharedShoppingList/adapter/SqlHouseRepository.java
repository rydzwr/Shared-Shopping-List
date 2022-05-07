package com.rydzwr.sharedShoppingList.adapter;

import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.HouseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SqlHouseRepository extends HouseRepository, JpaRepository<House, Integer>
{
}
