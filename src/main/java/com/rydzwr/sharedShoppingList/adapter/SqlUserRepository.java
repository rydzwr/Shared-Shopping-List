package com.rydzwr.sharedShoppingList.adapter;

import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer>
{
}
