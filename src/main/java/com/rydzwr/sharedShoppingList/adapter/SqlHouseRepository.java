package com.rydzwr.sharedShoppingList.adapter;

import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.repository.HouseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlHouseRepository extends HouseRepository, JpaRepository<House, Integer>
{
}
