package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.mapper.UserMapper;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbUserService
{
    private final UserRepository repository;

    public DbUserService(UserRepository repository)
    {
        this.repository = repository;
    }

    public String getName(int id)
    {
        return repository.findById(id).get().getName();
    }

    public List<Product> getAllProducts(int id)
    {
        return repository.findById(id).get().getProductsList();
    }

    public void removeAllProductsWhereBoughtIsTrue(int userId)
    {
        User user = repository.findById(userId).get();
        List<Product> products = user.getProductsList();

        for (Product product : products)
        {
            if (product.isBought() == true)
                products.remove(product);
        }

        repository.save(user);
    }

    // TO DO
    // delete product by id
}
