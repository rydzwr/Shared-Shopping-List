package com.rydzwr.sharedShoppingList.repository;

import com.rydzwr.sharedShoppingList.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository
{
    List<Product> findAll();

    Optional<Product> findById(Integer id);

    boolean existsById(Integer id);

    Product save(Product entity);
}
