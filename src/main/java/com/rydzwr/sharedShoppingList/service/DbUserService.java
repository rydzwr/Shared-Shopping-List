package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.dto.UserDto;
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

import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
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
        return !user.isEmpty();
    }

    public UserDto getByDeviceId(String deviceId)
    {
        User user = repository.getUserByDeviceId(deviceId).orElseThrow(() -> new IllegalArgumentException("House with given id not found"));
        UserDto dto = userMapper.mapToUserDto(user);
        return dto;
    }

    public String getName(int id)
    {
        User user = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("House with given id not found"));
        return user.getName();
    }

    public List<ProductDto> getAllProducts(int id)
    {
        return productMapper.mapToProductDtoList(productRepository.findAllByUser_Id(id));
    }

    public UserDto createUser(String deviceId, UserDto userDto)
    {
        User user = userMapper.mapToUser(userDto);
        user.setDeviceId(deviceId);
        repository.save(user);
        return userDto;
    }

    public JsonDoc getInviteCode(String deviceId)
    {
        Random random = new Random();
        String password = String.format("%04d", random.nextInt(10000));

        User user = repository.getUserByDeviceId(deviceId).orElseThrow(() -> new IllegalArgumentException("User with given id not found"));
        House house = user.getHouse();

        if (house == null)
            throw new IllegalArgumentException("User is not assigned to a house!");

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
    public List<ProductDto> removeAllProductsWhereBoughtIsTrue(int userId)
    {
        if (!repository.existsById(userId))
            throw new IllegalArgumentException("User with given ID doesn't exists!");
        else
        {
            User user = repository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User with given id not found"));
            List<Product> products = productRepository.findAllByUser_Id(userId);

            for (Product product : products)
            {
                if (product.isBought())
                {
                    productRepository.deleteProductById(product.getId());
                }
            }

            repository.save(user);

            return productMapper.mapToProductDtoList(products);
        }
    }
    @Transactional
    public List<ProductDto> deleteProductById(int productId)
    {
        if (!productRepository.existsById(productId))
            throw new IllegalArgumentException("Product with given ID doesn't exists!");

        else
        {
            Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product with given id not found"));
            User user = product.getUser();
            List<Product> products = user.getProductsList();

            productRepository.deleteProductById(productId);

            repository.save(user);

            return productMapper.mapToProductDtoList(products);
        }
    }
}
