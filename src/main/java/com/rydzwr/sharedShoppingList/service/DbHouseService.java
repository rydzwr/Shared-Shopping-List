package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.HouseDto;
import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.exceptions.IdNotFoundException;
import com.rydzwr.sharedShoppingList.exceptions.UserAlreadyAssignedToHouseException;
import com.rydzwr.sharedShoppingList.exceptions.UserNotAssignedToHouseException;
import com.rydzwr.sharedShoppingList.mapper.HouseMapper;
import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.JsonDoc;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.HouseRepository;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import com.rydzwr.sharedShoppingList.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DbHouseService
{
    private final HouseRepository repository;
    private final UserRepository userRepository;
    private final HouseMapper mapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserValidation userValidation;

    public DbHouseService(
            HouseRepository repository,
            UserRepository userRepository,
            HouseMapper mapper,
            ProductRepository productRepository,
            ProductMapper productMapper,
            UserValidation userValidation)
    {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userValidation = userValidation;
    }

    public HouseDto createHouse(HouseDto houseDto, String authHeader)
    {
        User user = userValidation.validate(authHeader);

        House house = mapper.mapToHouse(houseDto);
        house = repository.save(house);

        if (user.getHouse() != null)
            throw new UserAlreadyAssignedToHouseException("User already assigned to house!");

        user.setHouse(house);
        userRepository.save(user);

        return mapper.mapToHouseDto(house);
    }

    public boolean join(String inviteCode, String authHeader)
    {
        User user = userValidation.validate(authHeader);

        if (!repository.existsByPassword(inviteCode)) return false;

        else
        {
            House house = repository.getHouseByPassword(inviteCode);

            if (user.getHouse() != null)
                throw new UserAlreadyAssignedToHouseException("User is already assigned to house!");

            user.setHouse(house);
            userRepository.save(user);
            return true;
        }
    }

    public void update(String authHeader, HouseDto houseDto)
    {
        User user = userValidation.validate(authHeader);
        if (user.getHouse() == null)
            throw new UserNotAssignedToHouseException("User Is Not Assigned To House");
        else
        {
            House house = user.getHouse();
            house.updateName(mapper.mapToHouse(houseDto));
            repository.save(house);
        }
    }

    public JsonDoc completeProductsList(String authHeader)
    {
        User caller = userValidation.validate(authHeader);

        if (caller.getHouse() == null)
            throw new UserNotAssignedToHouseException("User Is Not Assigned To House");

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
    @Transactional
    public void clearHouse(String authHeader)
    {
        User user = userValidation.validate(authHeader);

        if (user.getHouse() == null)
            throw new UserNotAssignedToHouseException("User Is Not Assigned To House");

        House house = user.getHouse();
        productRepository.deleteAllByBoughtTrueAndUser_House(house);
        repository.save(house);
    }

    @Transactional
    public void removeUser(String authHeader)
    {
        User user = userValidation.validate(authHeader);
        productRepository.deleteAllByUser_Id(user.getId());

        House house = user.getHouse();
        if (house.getUsers().size() == 1) // Are we the last user in this house?
            repository.deleteById(house.getId());


        user.setHouse(null);
        userRepository.save(user);
    }
}
