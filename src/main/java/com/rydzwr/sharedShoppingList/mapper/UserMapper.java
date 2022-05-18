package com.rydzwr.sharedShoppingList.mapper;

import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.model.User;
import org.springframework.stereotype.Service;

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
}
