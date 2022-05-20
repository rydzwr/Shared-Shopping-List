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
        return repository.findById(id).get().getName();
    }

    public List<User> getUsers(int id)
    {
        return repository.findById(id).get().getUsers();
    }

    public String getPassword(int id)
    {
        // TO DO

        // 2 min validation

        Random random = new Random();
        House house = repository.findById(id).get();
        String password = String.format("%04d", random.nextInt(10000));
        house.setPassword(password);
        return house.getPassword();
    }

    public UserDto addUser(int id, UserDto userDto)
    {
        if (!repository.existsById(id) && !userRepository.existsById(id))
            throw new IllegalArgumentException("House or User with given ID doesn't exists!");

        else
        {
            User user = userRepository.findById(userDto.getId()).get();
            House house = repository.findById(id).get();
            house.getUsers().add(user);
            repository.save(house);
            return userDto;
        }
    }

    // Removes all bought products from all users
    public void clearHouse(int id)
    {
        House house = repository.findById(id).get();
        List<User> users = house.getUsers();
        for (User user : users)
        {
            List<Product> products = user.getProductsList();

            for (int i = 0; i < products.size(); i++)
            {
                if (products.get(i).isBought() == true)
                    productRepository.deleteProductById(products.remove(i).getId());
            }

            userRepository.save(user);
        }

        repository.save(house);
    }

    public void removeUser(int userId)
    {
       User user = userRepository.findById(userId).get();
       House house = user.getHouse();
       house.getUsers().remove(user);
       repository.save(house);
    }
}
