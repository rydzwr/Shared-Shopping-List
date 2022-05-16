package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;

import java.util.List;

public class DbProductService
{
    private final ProductRepository repository;

    public DbProductService(ProductRepository repository)
    {
        this.repository = repository;
    }

    public String getDetails(int id)
    {
        Product product = repository.findById(id).get();

        String result =
                        "name: " + product.getName() +
                        "description: " + product.getDescription() +
                        "important: " + product.isImportant() +
                        "bought: " + product.isBought();

        return result;
    }

    // TO DO
    // update details
    //setImportant
    //setBought
}
