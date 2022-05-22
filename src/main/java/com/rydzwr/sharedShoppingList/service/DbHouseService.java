package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.HouseDto;
import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.mapper.HouseMapper;
import com.rydzwr.sharedShoppingList.mapper.UserMapper;
import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.HouseRepository;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import com.rydzwr.sharedShoppingList.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public DbHouseService(HouseRepository repository, UserRepository userRepository, HouseMapper mapper, UserMapper userMapper, ProductRepository productRepository)
    {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.userMapper = userMapper;
        this.productRepository = productRepository;
    }

    public HouseDto createHouse(HouseDto houseDto)
    {
        House house = mapper.mapToHouse(houseDto);
        repository.save(house);

        return houseDto;
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

    public String getPassword(int id)
    {
        // TO DO

        // 2 min validation

        Random random = new Random();
        House house = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("House with given id not found"));
        String password = String.format("%04d", random.nextInt(10000));
        house.setPassword(password);
        repository.save(house);
        return house.getPassword();
    }

    // TO DO
    // add password validation

    public UserDto addUser(int id, UserDto userDto)
    {
        if (!repository.existsById(id) && !userRepository.existsById(id))
            throw new IllegalArgumentException("House or User with given ID doesn't exists!");

        else
        {
            User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new IllegalArgumentException("User with given id not found"));
            House house = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("House with given id not found"));
            house.getUsers().add(user);
            repository.save(house);
            return userDto;
        }
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
