package com.rydzwr.sharedShoppingList.repository;

import com.rydzwr.sharedShoppingList.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository
{
    List<User> findAll();

    Optional<User> findById(Integer id);

    Optional<User> findByUserName(String name);

    boolean existsById(Integer id);

    User save(User entity);
}
