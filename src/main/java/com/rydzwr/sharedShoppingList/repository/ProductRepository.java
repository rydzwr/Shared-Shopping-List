package com.rydzwr.sharedShoppingList.repository;

import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository
{
    List<Product> findAll();

    Optional<Product> findById(Integer id);

    List<Product> findAllByUser_Id(int userId);

    boolean existsById(Integer id);

    Product save(Product entity);

    void deleteProductById(Integer id);

    void deleteAllByBoughtTrueAndUser_House(House house);

    void deleteAllByUser_Id(int user_id);

    void deleteAllByBoughtTrueAndUser_Id(int userId);

    void deleteAllByBoughtTrueAndUser_DeviceId(String deviceId);
}
