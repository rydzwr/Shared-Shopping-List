package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.exceptions.IdNotFoundException;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidation
{
    final UserRepository repository;

    public UserValidation(UserRepository repository)
    {
        this.repository = repository;
    }

    public User validate(String authHeader)
    {
        String deviceId = DeviceAuthorization.getInstance().deviceIdFromAuthHeader(authHeader);
        User user = repository.getUserByDeviceId(deviceId).orElseThrow(() -> new IdNotFoundException("User with given Device ID not found"));
        return user;
    }
}
