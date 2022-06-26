package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.exceptions.IdNotFoundException;
import com.rydzwr.sharedShoppingList.exceptions.InvalidInputException;
import com.rydzwr.sharedShoppingList.exceptions.UserAlreadyAssignedToHouseException;
import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.mapper.UserMapper;
import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.JsonDoc;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.HouseRepository;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import com.rydzwr.sharedShoppingList.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DbUserService
{
    private final UserRepository repository;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final HouseRepository houseRepository;
    private final UserMapper userMapper;

    public DbUserService(UserRepository repository, ProductMapper productMapper, ProductRepository productRepository, UserMapper userMapper, HouseRepository houseRepository)
    {
        this.repository = repository;
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.userMapper = userMapper;
        this.houseRepository = houseRepository;
    }

    public boolean authorizeDevice(String authHeader)
    {
        String deviceId = DeviceAuthorization.getInstance().deviceIdFromAuthHeader(authHeader);

        Optional<User> user = repository.getUserByDeviceId(deviceId);
        return user.isPresent();
    }

    public UserDto getByDeviceId(String deviceId)
    {
        User user = repository.getUserByDeviceId(deviceId).orElseThrow(() -> new IdNotFoundException("User with given device ID not found"));
        return userMapper.mapToUserDto(user);
    }

    public UserDto createUser(String deviceId, UserDto userDto)
    {
        if (repository.existsByDeviceId(deviceId))
            throw new InvalidInputException("User With Given Device ID Already Exists!");

        User user = userMapper.mapToUser(userDto);
        user.setDeviceId(deviceId);
        repository.save(user);
        return userDto;
    }

    public UserDto renameUser(String deviceId, UserDto userDto)
    {
        User user = repository.getUserByDeviceId(deviceId).orElseThrow(() -> new IdNotFoundException("User with given device ID not found"));

        user.updateFrom(userMapper.mapToUser(userDto));
        user = repository.save(user);
        return userMapper.mapToUserDto(user);
    }

    public JsonDoc getInviteCode(String deviceId)
    {
        Random random = new Random();
        String password = String.format("%04d", random.nextInt(10000));

        User user = repository.getUserByDeviceId(deviceId).orElseThrow(() -> new IdNotFoundException("User with given device ID not found"));
        House house = user.getHouse();

        if (house == null)
            throw new UserAlreadyAssignedToHouseException("User is not assigned to a house!");

        house.setPassword(password);
        houseRepository.save(house);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                house.setPassword(null);
                houseRepository.save(house);
            }

        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 120000);

        JsonDoc res = new JsonDoc();
        res.put("inviteCode", password);
        return res;
    }

    @Transactional
    public void removeAllProductsWhereBoughtIsTrue(String deviceId)
    {
        User user = repository.getUserByDeviceId(deviceId).orElseThrow(() -> new IdNotFoundException("User with given device ID not found"));

        productRepository.deleteAllByBoughtTrueAndUser_DeviceId(deviceId);
        repository.save(user);
    }
}
