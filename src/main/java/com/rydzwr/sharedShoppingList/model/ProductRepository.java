package com.rydzwr.sharedShoppingList.model;

import java.util.List;
import java.util.Optional;

public interface ProductRepository
{
    List<Product> findAll();

    Optional<Product> findById(Integer id);

    boolean existsById(Integer id);

    Product save(Product entity);
}
