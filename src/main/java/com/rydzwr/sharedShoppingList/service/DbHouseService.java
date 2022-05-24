package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.HouseDto;
import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.mapper.HouseMapper;
import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.mapper.UserMapper;
import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.JsonDoc;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.HouseRepository;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import com.rydzwr.sharedShoppingList.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;
import java.util.Random;

@Service
public class DbHouseService
{
    private final HouseRepository repository;
    private final UserRepository userRepository;
    private final HouseMapper mapper;
    private final UserMapper userMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public DbHouseService(HouseRepository repository, UserRepository userRepository, HouseMapper mapper,
                          UserMapper userMapper, ProductRepository productRepository, ProductMapper productMapper)
    {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.userMapper = userMapper;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public HouseDto createHouse(HouseDto houseDto, String authHeader)
    {
        House house = mapper.mapToHouse(houseDto);
        house = repository.save(house);

        String deviceId = DeviceAuthorization.getInstance().deviceIdFromAuthHeader(authHeader);
        User user = userRepository.getUserByDeviceId(deviceId).orElseThrow(() -> new IllegalArgumentException("User with given device ID not found"));

        if (user.getHouse() != null)
            throw new IllegalStateException("User already assigned to house!");

        user.setHouse(house);
        userRepository.save(user);

        return mapper.mapToHouseDto(house);
    }

    public String getHouseName(int id)
    {
        House house = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("House with given id not found"));
        return house.getName();
    }

    public List<UserDto> getUsers(int id)
    {
        House house = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("House with given id not found"));
        return userMapper.mapToUserDtoList(house.getUsers());
    }



    // TO DO
    // add password validation

    public void join(String inviteCode, String deviceId)
    {
        User user = userRepository.getUserByDeviceId(deviceId).orElseThrow(() -> new IllegalArgumentException("User with given device ID not found"));
        House house = repository.getHouseByPassword(inviteCode);

        if (user.getHouse() != null)
            throw new IllegalStateException("User is already assigned to house!");

        user.setHouse(house);
        user = userRepository.save(user);
    }

    public JsonDoc completeProductsList(String deviceId)
    {
        User caller = userRepository.getUserByDeviceId(deviceId).orElseThrow(() -> new IllegalArgumentException("User with given device ID not found"));
        House house = caller.getHouse();

        JsonDoc res = new JsonDoc();
        res.put("houseName", house.getName());

        JsonDoc productsByUser = new JsonDoc();
        List<User> userList = house.getUsers();

        for (User user : userList)
        {
            List<ProductDto> productList = productMapper.mapToProductDtoList(user.getProductsList());
            productsByUser.put(user.getName(), productList.toArray());
        }

        res.put("productsByUser", productsByUser);
        return res;
    }

    // Removes all bought products from all users
    public void clearHouse(int id)
    {
        House house = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("House with given id not found"));
        List<User> users = house.getUsers();
        for (User user : users)
        {
            List<Product> products = user.getProductsList();

            for (int i = 0; i < products.size(); i++)
            {
                if (products.get(i).isBought())
                    productRepository.deleteProductById(products.remove(i).getId());
            }

            userRepository.save(user);
        }

        repository.save(house);
    }

    @Transactional
    public void removeUser(int userId)
    {
        if (!userRepository.existsById(userId))
            throw new IllegalArgumentException("User with given ID doesn't exists");

       else
        {
            userRepository.deleteUserById(userId);
        }
    }
}
