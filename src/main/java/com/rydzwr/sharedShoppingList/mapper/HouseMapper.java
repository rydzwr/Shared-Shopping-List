package com.rydzwr.sharedShoppingList.mapper;

import com.rydzwr.sharedShoppingList.dto.HouseDto;
import com.rydzwr.sharedShoppingList.model.House;
import org.springframework.stereotype.Service;

@Service
public class HouseMapper
{
    public House mapToHouse(final HouseDto houseDto)
    {
        return new House(
                houseDto.getId(),
                houseDto.getName()
        );
    }

    public HouseDto mapToHouseDto(final House house)
    {
        return new HouseDto(
                house.getId(),
                house.getName(),
                house.getPassword(),
                house.getUsers()
        );
    }
}
