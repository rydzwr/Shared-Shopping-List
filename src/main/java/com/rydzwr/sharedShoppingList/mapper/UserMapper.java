package com.rydzwr.sharedShoppingList.mapper;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper
{
    public User mapToUser(final UserDto userDto)
    {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getProductsList()
        );
    }

    public UserDto mapToUserDto(final User user)
    {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getProductsList()
        );
    }

    public UserDto mapUserNameToDto(final User user)
    {
        return new UserDto(
                user.getName()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> users)
    {
        List<UserDto> userDtos = new ArrayList<>();

        for (int i = 0; i < users.size(); i++)
        {
            userDtos.add(mapToUserDto(users.get(i)));
        }

        return userDtos;
    }
}
